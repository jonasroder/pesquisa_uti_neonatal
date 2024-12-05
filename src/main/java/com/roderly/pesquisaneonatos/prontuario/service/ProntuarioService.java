package com.roderly.pesquisaneonatos.prontuario.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.resistencia_microorganismo.repository.ResistenciaMicroorganismoRepository;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.neonato.mapper.NeonatoMapper;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoRepository;
import com.roderly.pesquisaneonatos.prontuario.dto.request.AntibiogramaIsoladoRequest;
import com.roderly.pesquisaneonatos.prontuario.dto.request.ColetaIsoladoRequest;
import com.roderly.pesquisaneonatos.prontuario.dto.request.EventoRequest;
import com.roderly.pesquisaneonatos.prontuario.dto.response.ColetaIsoladoResponse;
import com.roderly.pesquisaneonatos.prontuario.dto.response.ProntuarioResponse;
import com.roderly.pesquisaneonatos.prontuario.mapper.ProntuarioMapper;
import com.roderly.pesquisaneonatos.prontuario.model.AntibiogramaIsolado;
import com.roderly.pesquisaneonatos.prontuario.model.Evento;
import com.roderly.pesquisaneonatos.prontuario.model.IsoladoColeta;
import com.roderly.pesquisaneonatos.prontuario.repository.*;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final EventoRepository eventoRepository;
    private final EventoEntidadeRepository eventoEntidadeRepository;
    private final NeonatoRepository neonatoRepository;
    private final AntibiogramaIsoladoRepository antibiogramaIsoladoRepository;
    private final IsoladoColetaRepository isoladoColetaRepository;
    private final EventoViaAdministracaoRepository eventoViaAdministracaoRepository;
    private final ResistenciaMicroorganismoRepository resistenciaMicroorganismoRepository;

    public ProntuarioResponse load(Long id) {
        var neonato = neonatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Neonato não encontrado com ID: " + id));
        var neonatoResponse = NeonatoMapper.convertNeonatoToNeonatoListResponse(neonato);

        var eventos = eventoRepository.findByNeonatoIdNeonatoAndIsActiveTrue(id);
        var eventosList = eventos.stream()
                .map(ProntuarioMapper::eventoToEventoResponse)
                .collect(Collectors.toSet());

        return new ProntuarioResponse(neonatoResponse, eventosList);
    }


    public ApiResponseDTO salvarEvento(EventoRequest request) throws IOException {
        var evento = ProntuarioMapper.eventoRequestToEvento(request);
        var eventoSalvo = eventoRepository.save(evento);

        if (request.idEntidade() != null && eventoSalvo.getIdEvento() != null) {
            var eventoEntidade = ProntuarioMapper.eventoAndEventoRequestToEventoEntidade(request, eventoSalvo);
            eventoEntidadeRepository.save(eventoEntidade);
        }

        if (request.idViaAdministracao() != null && eventoSalvo.getIdEvento() != null) {
            var eventoViaAdministracao = ProntuarioMapper.eventoToeventoViaAdministracao(eventoSalvo, request.idEventoViaAdministracao(), request.idViaAdministracao());
            eventoViaAdministracaoRepository.save(eventoViaAdministracao);
        }

        return new ApiResponseDTO(eventoSalvo.getIdEvento(), "O registro foi Salvo!");
    }


    public List<ColetaIsoladoResponse> loadColetasProntuario(Long idNeonato) {
        var coletasSemAntibiogramas = eventoRepository.findColetaIsoladoByIdNeonato(idNeonato);

        return coletasSemAntibiogramas.stream()
                .map(coletaSemAntibiograma -> {
                    var antibiogramas = antibiogramaIsoladoRepository.findByIsoladoColetaId(coletaSemAntibiograma.idIsoladoColeta());
                    return ProntuarioMapper.mapToColetaIsoladoResponse(coletaSemAntibiograma, antibiogramas);
                })
                .collect(Collectors.toList());
    }


    public ApiResponseDTO salvarColetaIsolado(List<ColetaIsoladoRequest> request) throws IOException {

        Long idEvento = null;

        for (ColetaIsoladoRequest coletaIsoladoRequest : request) {
            var isoladoColeta = ProntuarioMapper.coletaIsoladoRequestToIsoladoColeta(coletaIsoladoRequest);
            var isolado = isoladoColetaRepository.save(isoladoColeta);
            idEvento = isolado.getEvento().getIdEvento();

            for (AntibiogramaIsoladoRequest antibiogramaIsolado : coletaIsoladoRequest.antibiogramas()) {

                if ((antibiogramaIsolado.idAntimicrobiano() == null && antibiogramaIsolado.idResistenciaMicroorganismo() == null) && antibiogramaIsolado.idAntibiogramaIsolado() == null) {
                    continue;
                }

                var antibiograma = ProntuarioMapper.antibiogramaIsoladoRequestToAntibiograma(antibiogramaIsolado, isolado);
                antibiogramaIsoladoRepository.save(antibiograma);
            }
        }

        verificarEExcluirDuplicatas(eventoRepository.findIdNeonatoByIdEvento(idEvento));

        return ApiResponseDTO.successMessage("O registro foi salvo!");
    }


    private void verificarEExcluirDuplicatas(Long idNeonato) {
        // Obtém os IsoladosColeta duplicados, respeitando a regra da query ajustada
        var listIsoladosColeta = eventoRepository.findDuplicatedIsoladoColeta(idNeonato);

        if (listIsoladosColeta.isEmpty()) {
            return; // Sem duplicatas para processar
        }

        // Agrupa os IsoladosColeta por campos relevantes
        Map<String, List<IsoladoColeta>> grupos = listIsoladosColeta.stream()
                .collect(Collectors.groupingBy(this::gerarChaveUnica));

        for (var entry : grupos.entrySet()) {
            List<IsoladoColeta> grupoDuplicado = entry.getValue();

            if (grupoDuplicado.size() > 1) {
                // Ordena os registros por dataEvento em ordem crescente
                grupoDuplicado.sort(Comparator.comparing(ic -> ic.getEvento().getDataEvento()));

                // Marca todos os registros como desconsiderados, exceto o primeiro (mais antigo)
                for (int i = 1; i < grupoDuplicado.size(); i++) {
                    var duplicado = grupoDuplicado.get(i);
                    duplicado.setDesconsiderarColeta(true);
                    isoladoColetaRepository.save(duplicado);
                }
            }
        }
    }


    private String gerarChaveUnica(IsoladoColeta isolado) {
        // Gera uma chave única baseada nos campos relevantes
        return isolado.getMicroorganismo().getIdMicroorganismo() + "-" +
                isolado.getPerfilResistenciaMicroorganismo().getIdPerfilResistenciaMicroorganismo() + "-" +
                isolado.getMecanismoResistenciaMicroorganismo().getIdMecanismoResistenciaMicroorganismo() + "-" +
                gerarChaveAntibiogramas(isolado.getAntibiogramasIsolado());
    }


    private String gerarChaveAntibiogramas(List<AntibiogramaIsolado> antibiogramas) {
        // Gera uma chave única para os antibiogramas, considerando antimicrobianos e resistências
        return antibiogramas.stream()
                .sorted(Comparator.comparing(a -> a.getAntimicrobiano().getIdAntimicrobiano()))
                .map(a -> a.getAntimicrobiano().getIdAntimicrobiano() + "-" + a.getResistenciaMicroorganismo().getIdResistenciaMicroorganismo())
                .collect(Collectors.joining("|"));
    }


    public ApiResponseDTO excluirEvento(Long idEvento) throws IOException {
        var evento = eventoRepository.findById(idEvento).orElseThrow(() -> new EntityNotFoundException("Evento não encontrado com id: " + idEvento));
        evento.setIsActive(false);
        eventoRepository.save(evento);

        return ApiResponseDTO.successMessage("O evento foi removido!");
    }

}
