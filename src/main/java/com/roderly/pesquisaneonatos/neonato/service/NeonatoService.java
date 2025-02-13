package com.roderly.pesquisaneonatos.neonato.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.model.Antimicrobiano;
import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.repository.AntimicrobianoRepository;
import com.roderly.pesquisaneonatos.cadastros_gerais.sitio_malformacao.model.SitioMalformacao;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.excel.ExcelService;
import com.roderly.pesquisaneonatos.common.excel.ExcelSheetData;
import com.roderly.pesquisaneonatos.neonato.dto.request.NeonatoRequest;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoListResponse;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoResponse;
import com.roderly.pesquisaneonatos.neonato.excel.*;
import com.roderly.pesquisaneonatos.neonato.mapper.NeonatoMapper;
import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import com.roderly.pesquisaneonatos.neonato.model.NeonatoAusenciaUTI;
import com.roderly.pesquisaneonatos.neonato.model.NeonatoSitioMalformacao;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoAusenciaUTIRepository;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoRepository;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoSitioMalformacaoRepository;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.ClasseAntimicrobianoCountProjection;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.EventoCountProjection;
import com.roderly.pesquisaneonatos.prontuario.dto.response.EventoTipoDiasResponse;
import com.roderly.pesquisaneonatos.prontuario.model.AntibiogramaIsolado;
import com.roderly.pesquisaneonatos.prontuario.model.Evento;
import com.roderly.pesquisaneonatos.prontuario.model.IsoladoColeta;
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
    private final AntimicrobianoRepository antimicrobianoRepository;
    private final EntityManager entityManager;

    private List<Antimicrobiano> antimicrobianos;

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

        antimicrobianos = antimicrobianoRepository.findAll();

        var neonatosControle = getReportGrupoControle();
        var mappingGrupoControle = ExcelHelper.createGrupoControleColumnMapping();

        var neonatosInfectados = getReportGrupoInfectado();
        var mappingGrupoInfectado = ExcelHelper.createGrupoInfectadoColumnMapping();

        var isolados = getReportIsolados();
        var mappingIsolados = ExcelHelper.createIsoladosColumnMapping();

        List<ExcelSheetData<?>> sheetDataList = new ArrayList<>();
        sheetDataList.add(new ExcelSheetData<>("Grupo Controle", neonatosControle, mappingGrupoControle));
        sheetDataList.add(new ExcelSheetData<>("Grupo Infectado", neonatosInfectados, mappingGrupoInfectado));
        sheetDataList.add(new ExcelSheetData<>("Isolados", isolados, mappingIsolados));

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


    public long calcularDiasForaUTI(List<NeonatoAusenciaUTI> neonatoAusenciaUti) {
        return neonatoAusenciaUti.stream()
                .filter(projection -> projection.getDataSaidaUti() != null && projection.getDataRetornoUti() != null)
                .mapToLong(projection -> ChronoUnit.DAYS.between(projection.getDataSaidaUti(), projection.getDataRetornoUti()))
                .sum();
    }



    public List<NeonatoGrupoControleReportData> getReportGrupoControle() {
        var idsNeonatosControle = neonatoRepository.findIdsNeonatosControle();
        var neonatos = neonatoRepository.findAllById(idsNeonatosControle);

        return neonatos.stream()
                .map(neonato -> {
                    System.out.println("Report Controle IdNeonato: " + neonato.getIdNeonato());

                    var eventos = neonatoRepository.findEventoCountsByNeonato(neonato.getIdNeonato());
                    var classesAntimicrobianos = neonatoRepository.findClasseAntimicrobianoCountsByNeonato(neonato.getIdNeonato());
                    var diasUsoATB = neonatoRepository.getDiasUsoAntimicrobiano(neonato.getIdNeonato(), 1L);
                    var diasUsoATF = neonatoRepository.getDiasUsoAntimicrobiano(neonato.getIdNeonato(), 2L);
                    var cirurgiaNeonato = eventoRepository.getCirugiasNeonato(neonato.getIdNeonato()) > 0 ? 1L : 0L;

                    return NeonatoMapper.convertToNeonatoGrupoControleReportData(neonato, cirurgiaNeonato, eventos, diasUsoATB, diasUsoATF, classesAntimicrobianos, this);
                })
                .toList();
    }


    public List<NeonatoGrupoInfectadoReportData> getReportGrupoInfectado() {
        var idsNeonatosControle = neonatoRepository.findIdsNeonatosInfectados();
        var neonatos = neonatoRepository.findAllById(idsNeonatosControle);

        return neonatos.stream()
                .map(neonato -> {
                    System.out.println("Report Infectado IdNeonato: " + neonato.getIdNeonato());

                    return NeonatoMapper.convertToNeonatoGrupoInfectadoReportData(neonato, this);
                })
                .toList();
    }


    public List<Evento> filtrarListaEventosPorTipo(List<Evento> eventos, Long tipo) {
        return eventos.stream()
                .filter(evento -> evento.getTipoEvento().getIdTipoEvento().equals(tipo))
                .toList();
    }


    public List<Evento> buscarColetasInfeccao(List<Evento> eventos) {
        return eventos.stream()
                .filter(evento -> {
                    if (evento.getTipoEvento() == null || !evento.getTipoEvento().getIdTipoEvento().equals(10L)) {
                        return false;
                    }
                    if (evento.getIsoladoColeta() == null) {
                        return false;
                    }
                    return !evento.getIsoladoColeta().getDesconsiderarColeta();
                })
                .toList();
    }


    public ProcedimentosDiasInfeccao getProcedimentosDiasInfeccao(List<Evento> eventos, List<LocalDate> datasInfeccao) {
        var procedimentosDiasInfeccao = new ProcedimentosDiasInfeccao();

        procedimentosDiasInfeccao.setUso(eventos.isEmpty() ? 0 : 1);
        procedimentosDiasInfeccao.setDiasTotaisUso(eventos.size());

        // Processa dias até a infecção e dias após a infecção para cada episódio
        for (int i = 0; i < NeonatoMapper.numeroAnalisesInfeccoes; i++) {
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
                        !evento.getDataEvento().isBefore(dataInfeccao))
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
                .filter(evento -> evento.getTipoEvento().getIdTipoEvento().equals(10L) && evento.getEventoEntidade().getIdEntidade().equals(sitioColeta)
                        && evento.getTipoEvento().getIsActive())
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
                        coleta.setTipoIsolado(isoladoColeta.getMicroorganismo().getClassificacaoMicroorganismo().getCodigo());
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
                        coleta.setTipoIsolado2(isoladoColeta2.getMicroorganismo().getClassificacaoMicroorganismo().getCodigo());
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

            // Verificar se o a cirurgia tem sitio definido
            if (evento.getEventoEntidade() == null) {
                System.out.println("Evento com EventoEntidade nulo. ID do evento: " + evento.getIdEvento());
                continue;
            }

            var codigoSitioCirurgia = getCodigoCadastro("sitio_cirurgia", "id_sitio_cirurgia", evento.getEventoEntidade().getIdEntidade());

            var cirurgia = new DataCodigoCirurgia();
            cirurgia.setDataCirurgia(DateUtil.LocalDateToDateBR(dataEvento));
            cirurgia.setCodigoSitioCirurgia(codigoSitioCirurgia);
            colunasCirurgias.add(cirurgia);
        }

        return colunasCirurgias;
    }


    public List<MedicamentosDiasInfeccao> getMedicamentosEpisodiosInfeccao(List<Evento> episodios, List<LocalDate> datasInfeccao) {
        List<MedicamentosDiasInfeccao> medicamentosDias = new ArrayList<>();

        for (int i = 0; i < NeonatoMapper.numeroAnalisesInfeccoes; i++) {
            if (i < datasInfeccao.size() && datasInfeccao.get(i) != null) {
                var dataInfeccao = datasInfeccao.get(i);

                var eventosAteInfeccao = getEventosAteInfeccao(episodios, dataInfeccao);
                var eventosAposInfeccao = getEventosAposInfeccao(episodios, dataInfeccao);

                var antibioticosPre = getTipoMedicacao(eventosAteInfeccao, antimicrobianos, 1L);
                var antibioticosPos = getTipoMedicacao(eventosAposInfeccao, antimicrobianos, 1L);
                var antifungicosPre = getTipoMedicacao(eventosAteInfeccao, antimicrobianos, 2L);
                var antifungicosPos = getTipoMedicacao(eventosAposInfeccao, antimicrobianos, 2L);

                var aminoglicosideosPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 1L);
                var aminoglicosideosPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 1L);

                var ansamicinasPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 2L);
                var ansamicinasPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 2L);

                var betalactamicosPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 3L);
                var betalactamicosPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 3L);

                var carbapenemicosPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 4L);
                var carbapenemicosPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 4L);

                var cefalosporinasPrimeiraGeracaoPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 5L);
                var cefalosporinasPrimeiraGeracaoPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 5L);

                var cefalosporinasSegundaGeracaoPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 6L);
                var cefalosporinasSegundaGeracaoPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 6L);

                var cefalosporinasTerceiraGeracaoPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 7L);
                var cefalosporinasTerceiraGeracaoPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 7L);

                var cefalosporinasQuartaGeracaoPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 8L);
                var cefalosporinasQuartaGeracaoPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 8L);

                var cefalosporinasQuintaGeracaoPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 9L);
                var cefalosporinasQuintaGeracaoPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 9L);

                var glicilciclinasPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 10L);
                var glicilciclinasPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 10L);

                var glicopeptideosPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 11L);
                var glicopeptideosPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 11L);

                var inibidoresDeFolatoPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 12L);
                var inibidoresDeFolatoPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 12L);

                var lincosamidasPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 13L);
                var lincosamidasPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 13L);

                var macrolideosPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 14L);
                var macrolideosPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 14L);

                var nitrofuranicosPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 15L);
                var nitrofuranicosPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 15L);

                var nitroimidazoisPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 16L);
                var nitroimidazoisPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 16L);

                var polimixinasPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 17L);
                var polimixinasPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 17L);

                var quinolonasPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 18L);
                var quinolonasPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 18L);

                var tetraciclinasPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 19L);
                var tetraciclinasPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 19L);

                var azoisPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 20L);
                var azoisPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 20L);

                var equinocandinasPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 21L);
                var equinocandinasPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 21L);

                var polienosPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 22L);
                var polienosPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 22L);

                var medicamentos = new MedicamentosDiasInfeccao();

                medicamentos.setUsoAtbPrevio(antibioticosPre.isEmpty() ? 0 : 1);
                medicamentos.setUsoAtfPrevio(antifungicosPre.isEmpty() ? 0 : 1);
                medicamentos.setUsoAtbApos(antibioticosPos.isEmpty() ? 0 : 1);
                medicamentos.setUsoAtfApos(antifungicosPos.isEmpty() ? 0 : 1);
                medicamentos.setUso3MaisAtbPrevio(antibioticosPre.size() >= 3 ? 1 : 0);
                medicamentos.setUso3MaisAtfPrevio(antifungicosPre.size() >= 3 ? 1 : 0);
                medicamentos.setUso3MaisAtbApos(antibioticosPos.size() >= 3 ? 1 : 0);
                medicamentos.setUso3MaisAtfApos(antifungicosPos.size() >= 3 ? 1 : 0);
                medicamentos.setNomesAtbPrevio(getDescricaoAntimicrobianos(antibioticosPre, antimicrobianos));
                medicamentos.setNomesAtfPrevio(getDescricaoAntimicrobianos(antifungicosPre, antimicrobianos));
                medicamentos.setNomesAtbApos(getDescricaoAntimicrobianos(antibioticosPos, antimicrobianos));
                medicamentos.setNomesAtfApos(getDescricaoAntimicrobianos(antifungicosPos, antimicrobianos));
                medicamentos.setDiasTotaisAtbPrevio(contarEventosDatasUnicas(antibioticosPre));
                medicamentos.setDiasTotaisAtfPrevio(contarEventosDatasUnicas(antifungicosPre));
                medicamentos.setDiasTotaisAtbApos(contarEventosDatasUnicas(antibioticosPos));
                medicamentos.setDiasTotaisAtfApos(contarEventosDatasUnicas(antifungicosPos));
                medicamentos.setUsoAminoglicosideos(aminoglicosideosPos.isEmpty() && aminoglicosideosPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisAminoglicosideosPrevio(contarEventosDatasUnicas(aminoglicosideosPre));
                medicamentos.setDiasTotaisAminoglicosideosApos(contarEventosDatasUnicas(aminoglicosideosPos));

                medicamentos.setUsoAnsamicinas(ansamicinasPos.isEmpty() && ansamicinasPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisAnsamicinasPrevio(contarEventosDatasUnicas(ansamicinasPre));
                medicamentos.setDiasTotaisAnsamicinasApos(contarEventosDatasUnicas(ansamicinasPos));

                medicamentos.setUsoBetalactamicos(betalactamicosPos.isEmpty() && betalactamicosPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisBetalactamicosPrevio(contarEventosDatasUnicas(betalactamicosPre));
                medicamentos.setDiasTotaisBetalactamicosApos(contarEventosDatasUnicas(betalactamicosPos));

                medicamentos.setUsoCarbapenemicos(carbapenemicosPos.isEmpty() && carbapenemicosPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisCarbapenemicosPrevio(contarEventosDatasUnicas(carbapenemicosPre));
                medicamentos.setDiasTotaisCarbapenemicosApos(contarEventosDatasUnicas(carbapenemicosPos));

                medicamentos.setUsoCefalosporinasPrimeiraGeracao(cefalosporinasPrimeiraGeracaoPos.isEmpty() && cefalosporinasPrimeiraGeracaoPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisCefalosporinasPrimeiraGeracaoPrevio(contarEventosDatasUnicas(cefalosporinasPrimeiraGeracaoPre));
                medicamentos.setDiasTotaisCefalosporinasPrimeiraGeracaoApos(contarEventosDatasUnicas(cefalosporinasPrimeiraGeracaoPos));

                medicamentos.setUsoCefalosporinasSegundaGeracao(cefalosporinasSegundaGeracaoPos.isEmpty() && cefalosporinasSegundaGeracaoPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisCefalosporinasSegundaGeracaoPrevio(contarEventosDatasUnicas(cefalosporinasSegundaGeracaoPre));
                medicamentos.setDiasTotaisCefalosporinasSegundaGeracaoApos(contarEventosDatasUnicas(cefalosporinasSegundaGeracaoPos));

                medicamentos.setUsoCefalosporinasTerceiraGeracao(cefalosporinasTerceiraGeracaoPos.isEmpty() && cefalosporinasTerceiraGeracaoPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisCefalosporinasTerceiraGeracaoPrevio(contarEventosDatasUnicas(cefalosporinasTerceiraGeracaoPre));
                medicamentos.setDiasTotaisCefalosporinasTerceiraGeracaoApos(contarEventosDatasUnicas(cefalosporinasTerceiraGeracaoPos));

                medicamentos.setUsoCefalosporinasQuartaGeracao(cefalosporinasQuartaGeracaoPos.isEmpty() && cefalosporinasQuartaGeracaoPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisCefalosporinasQuartaGeracaoPrevio(contarEventosDatasUnicas(cefalosporinasQuartaGeracaoPre));
                medicamentos.setDiasTotaisCefalosporinasQuartaGeracaoApos(contarEventosDatasUnicas(cefalosporinasQuartaGeracaoPos));

                medicamentos.setUsoCefalosporinasQuintaGeracao(cefalosporinasQuintaGeracaoPos.isEmpty() && cefalosporinasQuintaGeracaoPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisCefalosporinasQuintaGeracaoPrevio(contarEventosDatasUnicas(cefalosporinasQuintaGeracaoPre));
                medicamentos.setDiasTotaisCefalosporinasQuintaGeracaoApos(contarEventosDatasUnicas(cefalosporinasQuintaGeracaoPos));

                medicamentos.setUsoGlicilciclinas(glicilciclinasPos.isEmpty() && glicilciclinasPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisGlicilciclinasPrevio(contarEventosDatasUnicas(glicilciclinasPre));
                medicamentos.setDiasTotaisGlicilciclinasApos(contarEventosDatasUnicas(glicilciclinasPos));

                medicamentos.setUsoGlicopeptideos(glicopeptideosPos.isEmpty() && glicopeptideosPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisGlicopeptideosPrevio(contarEventosDatasUnicas(glicopeptideosPre));
                medicamentos.setDiasTotaisGlicopeptideosApos(contarEventosDatasUnicas(glicopeptideosPos));

                medicamentos.setUsoInibidoresDeFolato(inibidoresDeFolatoPos.isEmpty() && inibidoresDeFolatoPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisInibidoresDeFolatoPrevio(contarEventosDatasUnicas(inibidoresDeFolatoPre));
                medicamentos.setDiasTotaisInibidoresDeFolatoApos(contarEventosDatasUnicas(inibidoresDeFolatoPos));

                medicamentos.setUsoLincosamidas(lincosamidasPos.isEmpty() && lincosamidasPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisLincosamidasPrevio(contarEventosDatasUnicas(lincosamidasPre));
                medicamentos.setDiasTotaisLincosamidasApos(contarEventosDatasUnicas(lincosamidasPos));

                medicamentos.setUsoMacrolideos(macrolideosPos.isEmpty() && macrolideosPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisMacrolideosPrevio(contarEventosDatasUnicas(macrolideosPre));
                medicamentos.setDiasTotaisMacrolideosApos(contarEventosDatasUnicas(macrolideosPos));

                medicamentos.setUsoNitrofuranicos(nitrofuranicosPos.isEmpty() && nitrofuranicosPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisNitrofuranicosPrevio(contarEventosDatasUnicas(nitrofuranicosPre));
                medicamentos.setDiasTotaisNitrofuranicosApos(contarEventosDatasUnicas(nitrofuranicosPos));

                medicamentos.setUsoNitroimidazois(nitroimidazoisPos.isEmpty() && nitroimidazoisPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisNitroimidazoisPrevio(contarEventosDatasUnicas(nitroimidazoisPre));
                medicamentos.setDiasTotaisNitroimidazoisApos(contarEventosDatasUnicas(nitroimidazoisPos));

                medicamentos.setUsoPolimixinas(polimixinasPos.isEmpty() && polimixinasPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisPolimixinasPrevio(contarEventosDatasUnicas(polimixinasPre));
                medicamentos.setDiasTotaisPolimixinasApos(contarEventosDatasUnicas(polimixinasPos));

                medicamentos.setUsoQuinolonas(quinolonasPos.isEmpty() && quinolonasPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisQuinolonasPrevio(contarEventosDatasUnicas(quinolonasPre));
                medicamentos.setDiasTotaisQuinolonasApos(contarEventosDatasUnicas(quinolonasPos));

                medicamentos.setUsoTetraciclinas(tetraciclinasPos.isEmpty() && tetraciclinasPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisTetraciclinasPrevio(contarEventosDatasUnicas(tetraciclinasPre));
                medicamentos.setDiasTotaisTetraciclinasApos(contarEventosDatasUnicas(tetraciclinasPos));

                medicamentos.setUsoAzois(azoisPos.isEmpty() && azoisPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisAzoisPrevio(contarEventosDatasUnicas(azoisPre));
                medicamentos.setDiasTotaisAzoisApos(contarEventosDatasUnicas(azoisPos));

                medicamentos.setUsoEquinocandinas(equinocandinasPos.isEmpty() && equinocandinasPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisEquinocandinasPrevio(contarEventosDatasUnicas(equinocandinasPre));
                medicamentos.setDiasTotaisEquinocandinasApos(contarEventosDatasUnicas(equinocandinasPos));

                medicamentos.setUsoPolienos(polienosPos.isEmpty() && polienosPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisPolienosPrevio(contarEventosDatasUnicas(polienosPre));
                medicamentos.setDiasTotaisPolienosApos(contarEventosDatasUnicas(polienosPos));

                medicamentosDias.add(medicamentos);
            }
        }

        return medicamentosDias;
    }


    public List<Evento> getEventosAteInfeccao(List<Evento> eventos, LocalDate dataInfeccao) {
        return eventos.stream()
                .filter(evento -> evento.getTipoEvento().getIsActive() &&
                        evento.getDataEvento().isBefore(dataInfeccao))
                .toList();
    }


    public List<Evento> getEventosAposInfeccao(List<Evento> eventos, LocalDate dataInfeccao) {
        return eventos.stream()
                .filter(evento -> evento.getTipoEvento().getIsActive() &&
                        !evento.getDataEvento().isBefore(dataInfeccao))
                .toList();
    }



    public List<Evento> getTipoMedicacao(List<Evento> eventos, List<Antimicrobiano> antimicrobianos, Long idTipoMedicacao) {
        return eventos.stream()
                .filter(evento -> evento.getEventoEntidade() != null)
                .filter(evento -> {
                    return antimicrobianos.stream()
                            .filter(antimicrobiano -> antimicrobiano.getIdAntimicrobiano().equals(evento.getEventoEntidade().getIdEntidade()))
                            .anyMatch(antimicrobiano ->
                                    antimicrobiano.getClasseAntimicrobiano() != null &&
                                            antimicrobiano.getClasseAntimicrobiano().getTipoAntimicrobiano() != null &&
                                            idTipoMedicacao.equals(antimicrobiano.getClasseAntimicrobiano().getTipoAntimicrobiano().getCodigo())
                            );
                })
                .toList();
    }



    public List<Evento> getEventosPorClasseAntimicrobiano(List<Evento> eventos, List<Antimicrobiano> antimicrobianos, Long idClasseAntimicrobiano) {
        return eventos.stream()
                .filter(evento -> evento.getEventoEntidade() != null)
                .filter(evento ->
                        antimicrobianos.stream()
                                .filter(antimicrobiano -> antimicrobiano.getIdAntimicrobiano().equals(evento.getEventoEntidade().getIdEntidade()))
                                .anyMatch(antimicrobiano ->
                                        antimicrobiano.getClasseAntimicrobiano() != null &&
                                                antimicrobiano.getClasseAntimicrobiano().getIdClasseAntimicrobano().equals(idClasseAntimicrobiano)
                                )
                )
                .toList();
    }


    public String getDescricaoAntimicrobianos(List<Evento> eventos, List<Antimicrobiano> antimicrobianos) {
        return eventos.stream()
                .map(evento ->
                        antimicrobianos.stream()
                                .filter(antimicrobiano -> antimicrobiano.getIdAntimicrobiano().equals(evento.getEventoEntidade().getIdEntidade()))
                                .findFirst()
                                .map(Antimicrobiano::getDescricao)
                                .orElse(null)
                )
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.joining(", "));
    }


    public Integer contarEventosDatasUnicas(List<Evento> eventos) {
        return (int) eventos.stream()
                .map(Evento::getDataEvento)
                .distinct()
                .count();
    }


    public List<IsoladosReportData> getReportIsolados() {
        var idsNeonatosControle = neonatoRepository.findIdsNeonatosInfectados();
        var neonatos = neonatoRepository.findAllById(idsNeonatosControle);
        var isolados = getIsoladoColetaFromNeonatos(neonatos);

        return isolados.stream()
                .map(isolado -> {
                    return NeonatoMapper.convertToIsoladosReportData(isolado, this);
                })
                .toList();
    }


    public List<IsoladoColeta> getIsoladoColetaFromNeonatos(List<Neonato> neonatos) {
        return neonatos.stream()
                .flatMap(neonato -> neonato.getEventoList().stream())
                .map(Evento::getIsoladoColeta)
                .filter(isoladoColeta -> isoladoColeta != null && !isoladoColeta.getDesconsiderarColeta() && isoladoColeta.getEvento().getIsActive())
                .toList();
    }



    public Long verificarNResistencia(List<AntibiogramaIsolado> antibiogramaIsolados) {
        if (antibiogramaIsolados.size() < 3)
            return 0L;

        int i = 0;
        for (AntibiogramaIsolado antibiograma : antibiogramaIsolados) {
            if (antibiograma.getResistenciaMicroorganismo() != null && antibiograma.getResistenciaMicroorganismo().getIdResistenciaMicroorganismo() == 1L) {
                i++;
            }
        }

        return i < 3 ? 0L : 1L;
    }



    public Long verificarResistenciaClasseAntimicrobiano(List<AntibiogramaIsolado> antibiogramas, Long idClasseAntimicrobiano) {
        return antibiogramas.stream()
                .filter(isolado -> isolado.getResistenciaMicroorganismo() != null)
                .filter(isolado -> isolado.getAntimicrobiano() != null)
                .filter(isolado -> isolado.getAntimicrobiano().getClasseAntimicrobiano() != null)
                .filter(isolado -> idClasseAntimicrobiano.equals(
                        isolado.getAntimicrobiano().getClasseAntimicrobiano().getIdClasseAntimicrobano()))
                .map(isolado -> {
                    Long idResistencia = isolado.getResistenciaMicroorganismo().getIdResistenciaMicroorganismo();
                    return idResistencia != null && idResistencia == 2L ? 1L : 0L;
                })
                .findFirst()
                .orElse(null);
    }




    public Long verificarResistenciaAntimicrobiano(List<AntibiogramaIsolado> antibiogramas, Long idAntimicrobiano) {
        return antibiogramas.stream()
                .filter(isolado -> isolado.getResistenciaMicroorganismo() != null)
                .filter(isolado -> isolado.getAntimicrobiano() != null)
                .filter(isolado -> idAntimicrobiano.equals(isolado.getAntimicrobiano().getIdAntimicrobiano()))
                .map(isolado -> {
                    Long idResistencia = isolado.getResistenciaMicroorganismo().getIdResistenciaMicroorganismo();
                    return idResistencia != null && idResistencia == 2L ? 1L : 0L;
                })
                .findFirst()
                .orElse(null);
    }







}
