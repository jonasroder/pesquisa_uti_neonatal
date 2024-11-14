package com.roderly.pesquisaneonatos.neonato.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.sitio_malformacao.model.SitioMalformacao;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.excel.ExcelService;
import com.roderly.pesquisaneonatos.common.excel.ExcelSheetData;
import com.roderly.pesquisaneonatos.neonato.dto.projections.DiasAusenciaUTIProjection;
import com.roderly.pesquisaneonatos.neonato.dto.request.NeonatoRequest;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoListResponse;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoResponse;
import com.roderly.pesquisaneonatos.neonato.excel.*;
import com.roderly.pesquisaneonatos.neonato.mapper.NeonatoMapper;
import com.roderly.pesquisaneonatos.neonato.model.NeonatoSitioMalformacao;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoAusenciaUTIRepository;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoRepository;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoSitioMalformacaoRepository;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.ClasseAntimicrobianoCountProjection;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.EventoCountProjection;
import com.roderly.pesquisaneonatos.prontuario.dto.response.EventoTipoDiasResponse;
import com.roderly.pesquisaneonatos.prontuario.model.Evento;
import com.roderly.pesquisaneonatos.prontuario.repository.EventoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NeonatoService {

    private final NeonatoRepository neonatoRepository;
    private final NeonatoAusenciaUTIRepository neonatoAusenciaUtiRepository;
    private final EventoRepository eventoRepository;
    private final NeonatoSitioMalformacaoRepository neonatoSitioMalformacaoRepository;
    private final EntityManager entityManager;


    public ApiResponseDTO save(NeonatoRequest request) throws IOException {
        var prontuarioExistente = neonatoRepository.findByProntuario(request.prontuario());
        if (prontuarioExistente.isPresent() && request.idNeonato() == null) {
            return ApiResponseDTO.failure("Número do prontuário já existente!");
        }

        var neonato = NeonatoMapper.convertNeonatoRequestToNeonato(request);
        var neonatoSalvo = neonatoRepository.save(neonato);

        var ausenciasUti = request.ausenciaUTI().stream()
                .filter(ausenciaRequest -> ausenciaRequest.dataSaidaUti() != null || ausenciaRequest.dataRetornoUti() != null)
                .map(ausenciaRequest -> NeonatoMapper.neonatoAusenciaUTIrequestToNeonatoAusenciaUTI(ausenciaRequest, neonatoSalvo))
                .toList();
        neonatoAusenciaUtiRepository.saveAll(ausenciasUti);


        var malformacaoList = request.idSitioMalformacao().stream()
                .map(idStioMalformacao -> NeonatoMapper.neonatoToNeonatoSitioMalformacao(idStioMalformacao, neonatoSalvo))
                .toList();

        //Deleta as opções antigas do autocomplete para salvar e/ou atualizar
        neonatoSitioMalformacaoRepository.deleteByNeonato(neonatoSalvo);
        neonatoSitioMalformacaoRepository.saveAll(malformacaoList);

        return new ApiResponseDTO(neonatoSalvo.getIdNeonato(), "O registro foi salvo!");
    }


    public NeonatoResponse load(Long id) {
        var neonato = neonatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Neonato não encontrado com ID: " + id));
        return NeonatoMapper.convertNeonatoToNeonatoResponse(neonato);
    }


    public List<NeonatoListResponse> list() {
        var neonatoList = neonatoRepository.findAll();

        return neonatoList.stream()
                .map(NeonatoMapper::convertNeonatoToNeonatoListResponse)
                .toList();

    }


    public byte[] generateExcelReport() throws IOException {

        var neonatosControle = getReportGrupoControle();
        var mappingGrupoControle = ExcelHelper.createGrupoControleColumnMapping();

        var neonatosInfectados = getReportGrupoInfectado();
        var mappingGrupoInfectado = ExcelHelper.createGrupoInfectadoColumnMapping();

        List<ExcelSheetData<?>> sheetDataList = new ArrayList<>();
        sheetDataList.add(new ExcelSheetData<>("Grupo Controle", neonatosControle, mappingGrupoControle));
        sheetDataList.add(new ExcelSheetData<>("Grupo Infectado", neonatosInfectados, mappingGrupoInfectado));

        return new ExcelService().generateExcelReport(sheetDataList);
    }


    public EventoTipoDiasResponse getEventoTipoDias(List<EventoCountProjection> eventos, Long tipoEvento) {
        return eventos.stream()
                .filter(evento -> evento.getIdTipoEvento().equals(tipoEvento))
                .findFirst()
                .map(eventoCountProjection -> new EventoTipoDiasResponse(
                        1L,
                        eventoCountProjection.getNEventos()
                ))
                .orElseGet(() -> new EventoTipoDiasResponse(0L, 0L));
    }


    public EventoTipoDiasResponse getClasseAnimicrobianoDias(List<ClasseAntimicrobianoCountProjection> antimicrobianos, Long idClasseAntimicrobiano) {
        return antimicrobianos.stream()
                .filter(evento -> evento.getIdClasseAntimicrobiano().equals(idClasseAntimicrobiano))
                .findFirst()
                .map(eventoCountProjection -> new EventoTipoDiasResponse(
                        1L,
                        eventoCountProjection.getNEventos()
                ))
                .orElseGet(() -> new EventoTipoDiasResponse(0L, 0L));
    }


    public long calcularDiasForaUTI(Long idNeonato) {
        List<DiasAusenciaUTIProjection> diasForaUti = neonatoAusenciaUtiRepository.findDiasAusenciaUTI(idNeonato);

        return diasForaUti.isEmpty() ? 0 :
                diasForaUti.stream()
                        .mapToLong(projection -> ChronoUnit.DAYS.between(projection.getDataSaida(), projection.getDataRetorno()))
                        .sum();
    }


    public List<NeonatoGrupoControleReportData> getReportGrupoControle() {
        var idsNeonatosControle = neonatoRepository.findIdsNeonatosControle();
        var neonatos = neonatoRepository.findAllById(idsNeonatosControle);

        return neonatos.stream()
                .map(neonato -> {
                    var eventos = neonatoRepository.findEventoCountsByNeonato(neonato.getIdNeonato());
                    var classesAntimicrobianos = neonatoRepository.findClasseAntimicrobianoCountsByNeonato(neonato.getIdNeonato());
                    var diasUsoATB = neonatoRepository.getDiasUsoAntimicrobiano(neonato.getIdNeonato(), 1L);
                    var diasUsoATF = neonatoRepository.getDiasUsoAntimicrobiano(neonato.getIdNeonato(), 2L);
                    var diasForaUti = calcularDiasForaUTI(neonato.getIdNeonato());
                    var cirurgiaNeonato = eventoRepository.getCirugiasNeonato(neonato.getIdNeonato()) > 0 ? 1L : 0L;

                    return NeonatoMapper.convertToNeonatoGrupoControleReportData(neonato, diasForaUti, cirurgiaNeonato, eventos, diasUsoATB, diasUsoATF, classesAntimicrobianos, this);
                })
                .toList();
    }


    public List<NeonatoGrupoInfectadoReportData> getReportGrupoInfectado() {
        var idsNeonatosControle = neonatoRepository.findIdsNeonatosInfectados();
        var neonatos = neonatoRepository.findAllById(idsNeonatosControle);

        return neonatos.stream()
                .map(neonato -> {
                    var diasForaUti = calcularDiasForaUTI(neonato.getIdNeonato());

                    return NeonatoMapper.convertToNeonatoGrupoInfectadoReportData(neonato, diasForaUti, this);
                })
                .toList();
    }


    public List<Evento> filtrarListaEventosPorTipo(List<Evento> eventos, Long tipo) {
        return eventos.stream()
                .filter(evento -> evento.getTipoEvento().getIdTipoEvento().equals(tipo) && evento.getTipoEvento().getIsActive())
                .toList();
    }


    public List<Evento> buscarColetasInfeccao(List<Evento> eventos) {
        return eventos.stream()
                .filter(evento -> evento.getTipoEvento().getIdTipoEvento().equals(10L) && evento.getTipoEvento().getIsActive() && evento.getIsoladoColeta() != null)
                .toList();
    }

    public ProcedimentosDiasInfeccao getProcedimentosDiasInfeccao(List<Evento> eventos, List<LocalDate> datasInfeccao) {
        var procedimentosDiasInfeccao = new ProcedimentosDiasInfeccao();

        procedimentosDiasInfeccao.setUso(eventos.isEmpty() ? 0 : 1);
        procedimentosDiasInfeccao.setDiasTotaisUso(eventos.size());

        // Processa dias até a infecção e dias após a infecção para cada episódio
        for (int i = 0; i < 7; i++) {
            if (i < datasInfeccao.size() && datasInfeccao.get(i) != null) {
                LocalDate dataInfeccao = datasInfeccao.get(i);

                // Define os dias até a infecção
                procedimentosDiasInfeccao.setDiasAteInfecao(i + 1, getDiasEventoAteInfeccao(eventos, dataInfeccao));

                // Define os dias após a infecção
                procedimentosDiasInfeccao.setDiasAposInfecao(i + 1, getDiasEventoAposInfeccao(eventos, dataInfeccao));
            }
        }

        return procedimentosDiasInfeccao;
    }


    public LinkedHashSet<LocalDate> getDatasInfeccoes(List<Evento> eventos) {
        return eventos.stream()
                .filter(evento -> evento.getTipoEvento().getIdTipoEvento().equals(10L) && evento.getTipoEvento().getIsActive())
                .map(Evento::getDataEvento)
                .sorted()
                .limit(7)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }


    public int getDiasEventoAteInfeccao(List<Evento> eventos, LocalDate dataInfeccao) {
        return (int) eventos.stream()
                .filter(evento -> evento.getTipoEvento().getIsActive() &&
                        evento.getDataEvento().isBefore(dataInfeccao))
                .count();
    }


    public int getDiasEventoAposInfeccao(List<Evento> eventos, LocalDate dataInfeccao) {
        return (int) eventos.stream()
                .filter(evento -> evento.getTipoEvento().getIsActive() &&
                        evento.getDataEvento().isAfter(dataInfeccao))
                .count();
    }


    public Long getCodigoCadastro(String tipoEntidade, String pkTipoEntidade, Long idEntidade) {
        String queryStr = "SELECT codigo FROM " + tipoEntidade + " WHERE " + pkTipoEntidade + " = " + idEntidade;

        try {
            Query query = entityManager.createNativeQuery(queryStr);
            return (Long) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public List<Evento> filtrarListaColetasPorLocal(List<Evento> eventos, Long sitioColeta) {
        return eventos.stream()
                .filter(evento -> evento.getTipoEvento().getIdTipoEvento().equals(10L) && evento.getEventoEntidade().getIdEntidade().equals(sitioColeta) && evento.getTipoEvento().getIsActive())
                .toList();
    }


    public List<ColetaEpisodio> listaEpisodiosColeta(List<Evento> eventos) {

        List<ColetaEpisodio> episodios = new ArrayList<>();

        var dataEventos = eventos.stream()
                .filter(evento -> evento.getDataEvento() != null)
                .collect(Collectors.groupingBy(
                        Evento::getDataEvento,
                        TreeMap::new,
                        Collectors.toList()
                ));

        for (Map.Entry<LocalDate, List<Evento>> entry : dataEventos.entrySet()) {
            LocalDate data = entry.getKey();
            List<Evento> eventosDoDia = entry.getValue();

            // Conta apenas os eventos que possuem isolados
            long nColetasComIsolados = eventosDoDia.stream()
                    .filter(evento -> evento.getIsoladoColeta() != null)
                    .count();

            var infeccaoMista = nColetasComIsolados > 1 ? 1 : 0;
            var coleta = new ColetaEpisodio();

            coleta.setDataInfeccao(DateUtil.LocalDateToDateBR(data));
            coleta.setNumeroIsolados((int) nColetasComIsolados); // Define o número de eventos com isolados

            // Verifica se há pelo menos um evento no dia e define o primeiro isolado, se presente
            if (!eventosDoDia.isEmpty()) {
                Evento primeiroEvento = eventosDoDia.get(0);
                if (primeiroEvento.getIsoladoColeta() != null) {
                    var isoladoColeta = primeiroEvento.getIsoladoColeta();

                    if (isoladoColeta.getMicroorganismo() != null) {
                        coleta.setEspecieIsolada(isoladoColeta.getMicroorganismo().getCodigo());
                    }
                    if (isoladoColeta.getPerfilResistenciaMicroorganismo() != null) {
                        coleta.setPerfilResistencia(isoladoColeta.getPerfilResistenciaMicroorganismo().getCodigo());
                    }
                    if (isoladoColeta.getMecanismoResistenciaMicroorganismo() != null) {
                        coleta.setMecanismoResistencia(isoladoColeta.getMecanismoResistenciaMicroorganismo().getCodigo());
                    }
                }
            }

            coleta.setInfeccaoMista(infeccaoMista);

            // Verifica se há pelo menos dois eventos com isolados para acessar o segundo isolado
            if (nColetasComIsolados > 1 && eventosDoDia.size() > 1) {
                Evento segundoEvento = eventosDoDia.get(1);
                if (segundoEvento.getIsoladoColeta() != null) {
                    var isoladoColeta2 = segundoEvento.getIsoladoColeta();

                    if (isoladoColeta2.getMicroorganismo() != null) {
                        coleta.setEspecieIsolada2(isoladoColeta2.getMicroorganismo().getCodigo());
                    }
                    if (isoladoColeta2.getPerfilResistenciaMicroorganismo() != null) {
                        coleta.setPerfilResistenciaEspecie2(isoladoColeta2.getPerfilResistenciaMicroorganismo().getCodigo());
                    }
                    if (isoladoColeta2.getMecanismoResistenciaMicroorganismo() != null) {
                        coleta.setMecanismoResistenciaEspecie2(isoladoColeta2.getMecanismoResistenciaMicroorganismo().getCodigo());
                    }
                }
            }

            episodios.add(coleta);
        }

        return episodios;
    }


    public List<SitioMalformacao> getColunasMalformacao(List<NeonatoSitioMalformacao> malformacaoList) {
        return malformacaoList.stream()
                .map(NeonatoSitioMalformacao::getSitioMalformacao)
                .toList();
    }


    public List<DataCodigoCirurgia> getColunasCirurgias(List<Evento> cirurgias) {

        List<DataCodigoCirurgia> colunasCirurgias = new ArrayList<>();

        for (Evento evento : cirurgias) {
            var dataEvento = evento.getDataEvento();
            var codigoSitioCirurgia = getCodigoCadastro("sitio_cirurgia", "id_sitio_cirurgia", evento.getEventoEntidade().getIdEntidade());

            var cirurgia = new DataCodigoCirurgia();
            cirurgia.setDataCirurgia(DateUtil.LocalDateToDateBR(dataEvento));
            cirurgia.setCodigoSitioCirurgia(codigoSitioCirurgia);
            colunasCirurgias.add(cirurgia);
        }

        return colunasCirurgias;
    }


}
