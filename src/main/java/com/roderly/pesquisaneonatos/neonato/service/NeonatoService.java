package com.roderly.pesquisaneonatos.neonato.service;

import com.roderly.pesquisaneonatos.common.dto.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.excel.ExcelService;
import com.roderly.pesquisaneonatos.common.excel.ExcelSheetData;
import com.roderly.pesquisaneonatos.neonato.dto.projections.DiasAusenciaUTIProjection;
import com.roderly.pesquisaneonatos.neonato.dto.request.NeonatoRequest;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoListResponse;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoResponse;
import com.roderly.pesquisaneonatos.neonato.excel.ExcelHelper;
import com.roderly.pesquisaneonatos.neonato.excel.NeonatoGrupoControleReportData;
import com.roderly.pesquisaneonatos.neonato.excel.NeonatoGrupoInfectadoReportData;
import com.roderly.pesquisaneonatos.neonato.mapper.NeonatoMapper;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoAusenciaUTIRepository;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoRepository;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoSitioMalformacaoRepository;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.ClasseAntimicrobianoCountProjection;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.EventoCountProjection;
import com.roderly.pesquisaneonatos.prontuario.dto.response.EventoTipoDiasResponse;
import com.roderly.pesquisaneonatos.prontuario.model.Evento;
import com.roderly.pesquisaneonatos.prontuario.repository.EventoEntidadeRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NeonatoService {

    private final NeonatoRepository neonatoRepository;
    private final NeonatoAusenciaUTIRepository neonatoAusenciaUtiRepository;
    private final EventoRepository eventoRepository;
    private final NeonatoSitioMalformacaoRepository neonatoSitioMalformacaoRepository;
    private final EventoEntidadeRepository eventoEntidadeRepository;
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


    public LocalDate getDataPrimeiraInfeccao(List<Evento> eventos) {
        return eventos.stream()
                .filter(evento -> evento.getTipoEvento().getIdTipoEvento().equals(10L) && evento.getTipoEvento().getIsActive())
                .map(Evento::getDataEvento)
                .min(LocalDate::compareTo)
                .orElse(null);
    }


    public Long getExistenciaEventoPrevioInfeccao(List<Evento> eventos, LocalDate dataInfeccao) {
        boolean exists = eventos.stream()
                .anyMatch(evento -> evento.getTipoEvento().getIsActive() &&
                        evento.getDataEvento().isBefore(dataInfeccao));

        return exists ? 1L : 0L;
    }


    public int getDiasEventoAteInfeccao(List<Evento> eventos, LocalDate dataInfeccao) {
        return (int) eventos.stream()
                .filter(evento -> evento.getTipoEvento().getIsActive() &&
                        evento.getDataEvento().isBefore(dataInfeccao))
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


}
