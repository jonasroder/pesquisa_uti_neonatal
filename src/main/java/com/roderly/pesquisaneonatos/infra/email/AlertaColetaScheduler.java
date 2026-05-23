package com.roderly.pesquisaneonatos.infra.email;

import com.roderly.pesquisaneonatos.neonato.repository.NeonatoRepository;
import com.roderly.pesquisaneonatos.prontuario.repository.EventoRepository;
import com.roderly.pesquisaneonatos.usuario.model.Usuario;
import com.roderly.pesquisaneonatos.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlertaColetaScheduler {

    private static final int DIAS_SEM_COLETA = 3;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

    private final NeonatoRepository neonatoRepository;
    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;

    @Scheduled(cron = "0 0 8 * * *") // todos os dias às 08:00
    public void verificarFaltaDeColeta() {
        LocalDateTime limite = LocalDateTime.now().minusDays(DIAS_SEM_COLETA);
        var pagina = PageRequest.of(0, 1);

        UltimaColetaInfo ultimoNeonato = toInfo(neonatoRepository.findUltimaColeta(pagina));
        UltimaColetaInfo ultimoEvento  = toInfo(eventoRepository.findUltimaColeta(pagina));
        UltimaColetaInfo maisRecente   = UltimaColetaInfo.maisRecente(ultimoNeonato, ultimoEvento);

        if (maisRecente != null && !maisRecente.isAntesDe(limite)) {
            return; // coleta recente — tudo ok
        }

        String dataColeta    = maisRecente != null && maisRecente.criadoEm() != null
                ? maisRecente.criadoEm().format(FMT) : "nenhum registro encontrado";
        String responsavel   = maisRecente != null && maisRecente.nomeResponsavel() != null
                ? maisRecente.nomeResponsavel() : "não identificado";

        log.warn("Alerta de coleta: última entrada em {} por {}. Enviando e-mails.", dataColeta, responsavel);

        List<Usuario> usuarios = usuarioRepository.findAllByIsActiveTrue();
        String assunto = "Alerta: atualização de prontuários pendente há mais de " + DIAS_SEM_COLETA + " dias";

        for (Usuario u : usuarios) {
            try {
                String primeiroNome = u.getNomeCompleto() != null
                        ? u.getNomeCompleto().split(" ")[0] : "pesquisador(a)";
                emailService.enviarHtml(u.getUsuario(), assunto,
                        construirCorpoEmail(primeiroNome, dataColeta, responsavel));
                log.info("Alerta enviado para {}", u.getUsuario());
            } catch (Exception e) {
                log.error("Falha ao enviar alerta para {}: {}", u.getUsuario(), e.getMessage());
            }
        }
    }

    private UltimaColetaInfo toInfo(List<Object[]> rows) {
        if (rows == null || rows.isEmpty()) return null;
        Object[] row = rows.get(0);
        LocalDateTime data = row[0] != null ? (LocalDateTime) row[0] : null;
        String nome        = row[1] != null ? (String) row[1] : null;
        return new UltimaColetaInfo(data, nome);
    }

    private String construirCorpoEmail(String primeiroNome, String dataUltimaColeta, String responsavel) {
        return """
                <!DOCTYPE html>
                <html lang="pt-BR">
                <body style="margin:0;padding:0;background:#f1f5f9;font-family:Arial,sans-serif;">
                  <table width="100%%" cellpadding="0" cellspacing="0">
                    <tr><td align="center" style="padding:40px 16px;">
                      <table width="560" cellpadding="0" cellspacing="0"
                             style="background:#ffffff;border-radius:8px;overflow:hidden;
                                    box-shadow:0 2px 8px rgba(0,0,0,.08);">

                        <!-- cabeçalho -->
                        <tr>
                          <td style="background:#b91c1c;padding:28px 32px;">
                            <p style="margin:0;font-size:11px;color:rgba(255,255,255,.7);
                                      text-transform:uppercase;letter-spacing:1px;">
                              Portal de Pesquisa · Epidemiologia em UTI Neonatal
                            </p>
                            <h1 style="margin:8px 0 0;font-size:20px;color:#ffffff;font-weight:700;">
                              ⚠️ Atualização de Prontuários Pendente
                            </h1>
                          </td>
                        </tr>

                        <!-- corpo -->
                        <tr>
                          <td style="padding:32px;">
                            <p style="margin:0 0 20px;font-size:15px;color:#1e293b;line-height:1.6;">
                              Prezado(a) <strong>%s</strong>,
                            </p>

                            <p style="margin:0 0 16px;font-size:15px;color:#1e293b;line-height:1.7;">
                              Identificamos que nenhum novo cadastro ou evento clínico foi registrado
                              no sistema há mais de <strong>%d dias</strong>. A atualização contínua
                              dos prontuários é fundamental para garantir a <strong>qualidade e
                              integridade dos dados</strong> da pesquisa epidemiológica.
                            </p>

                            <p style="margin:0 0 24px;font-size:15px;color:#1e293b;line-height:1.7;">
                              Dados desatualizados podem comprometer análises clínicas, indicadores
                              de infecção e o acompanhamento dos pacientes internados na UTI Neonatal.
                              Por isso, solicitamos que verifique se há internações, eventos ou
                              coletas pendentes de registro.
                            </p>

                            <table width="100%%" cellpadding="0" cellspacing="0"
                                   style="background:#fef2f2;border:1px solid #fca5a5;
                                          border-radius:6px;margin-bottom:28px;">
                              <tr>
                                <td style="padding:16px 20px 8px;">
                                  <p style="margin:0;font-size:11px;color:#991b1b;
                                            text-transform:uppercase;letter-spacing:.5px;">
                                    Último registro no sistema
                                  </p>
                                  <p style="margin:6px 0 12px;font-size:17px;font-weight:700;color:#7f1d1d;">
                                    %s
                                  </p>
                                  <p style="margin:0;font-size:11px;color:#991b1b;
                                            text-transform:uppercase;letter-spacing:.5px;">
                                    Responsável pelo registro
                                  </p>
                                  <p style="margin:6px 0 0;font-size:15px;font-weight:600;color:#7f1d1d;">
                                    %s
                                  </p>
                                </td>
                              </tr>
                            </table>

                            <a href="https://neonatalepidemiology.com.br"
                               style="display:inline-block;background:#0d3a5c;color:#ffffff;
                                      text-decoration:none;padding:13px 28px;border-radius:6px;
                                      font-size:14px;font-weight:600;letter-spacing:.3px;">
                              Acessar o sistema →
                            </a>
                          </td>
                        </tr>

                        <!-- rodapé -->
                        <tr>
                          <td style="padding:16px 32px;border-top:1px solid #e2e8f0;">
                            <p style="margin:0;font-size:11px;color:#94a3b8;line-height:1.6;">
                              Mensagem automática — Portal de Pesquisa · Epidemiologia em UTI Neonatal<br>
                              Para deixar de receber estes alertas, entre em contato com o administrador do sistema.
                            </p>
                          </td>
                        </tr>

                      </table>
                    </td></tr>
                  </table>
                </body>
                </html>
                """.formatted(primeiroNome, DIAS_SEM_COLETA, dataUltimaColeta, responsavel);
    }
}
