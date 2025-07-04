package com.roderly.pesquisaneonatos.neonato.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.model.Antimicrobiano;
import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.repository.AntimicrobianoRepository;
import com.roderly.pesquisaneonatos.cadastros_gerais.sitio_malformacao.model.SitioMalformacao;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.excel.ExcelService;
import com.roderly.pesquisaneonatos.common.excel.ExcelSheetData;
import com.roderly.pesquisaneonatos.download.dto.request.FiltrosExcelRequest;
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
import com.roderly.pesquisaneonatos.neonato.specification.NeonatoSpecification;
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
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    private static final Set<Long> COLONIZACOES = Set.of(4L, 5L, 7L, 9L);

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
        var neonatoList = neonatoRepository.findByIsActiveTrueOrderByDataInternacaoDesc();

        return neonatoList.stream()
                .map(NeonatoMapper::convertNeonatoToNeonatoListResponse)
                .toList();
    }


    public ApiResponseDTO delete(Long id) {
        var neonato = neonatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Neonato não encontrado com ID: " + id));
        neonato.setIsActive(false);
        neonatoRepository.save(neonato);
        return ApiResponseDTO.successMessage("O Neonato foi Apagado com sucesso!");
    }


    public byte[] generateExcelReport(FiltrosExcelRequest filtrosRequest) throws IOException {

        antimicrobianos = antimicrobianoRepository.findAll();

        var neonatosControle = getReportGrupoControle(filtrosRequest);
        var mappingGrupoControle = ExcelHelper.createGrupoControleColumnMapping();

        var neonatosInfectados = getReportGrupoInfectado(filtrosRequest);
        var mappingGrupoInfectado = ExcelHelper.createGrupoInfectadoColumnMapping();

        var neonatosColonizados = getReportGrupoColonizado(filtrosRequest);
        var mappingGrupoColonizado = ExcelHelper.createGrupoColonizadoColumnMapping();

        var isolados = getReportIsolados(filtrosRequest);
        var mappingIsolados = ExcelHelper.createIsoladosColumnMapping();

        List<ExcelSheetData<?>> sheetDataList = new ArrayList<>();
        sheetDataList.add(new ExcelSheetData<>("Grupo Controle", neonatosControle, mappingGrupoControle));
        sheetDataList.add(new ExcelSheetData<>("Grupo Infectado", neonatosInfectados, mappingGrupoInfectado));
        sheetDataList.add(new ExcelSheetData<>("Grupo Colonizado", neonatosColonizados, mappingGrupoColonizado));
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
        if (neonatoAusenciaUti == null || neonatoAusenciaUti.isEmpty()) {
            return 0L;
        }

        return neonatoAusenciaUti.stream()
                .filter(item -> item.getDataSaidaUti() != null && item.getDataRetornoUti() != null)
                .mapToLong(item -> ChronoUnit.DAYS.between(item.getDataSaidaUti(), item.getDataRetornoUti()))
                .sum();
    }


    public List<NeonatoGrupoControleReportData> getReportGrupoControle(FiltrosExcelRequest filtrosRequest) {

        var neonatos = neonatoRepository.findAll(NeonatoSpecification.byFiltros(filtrosRequest, "controle"));

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


    public List<NeonatoGrupoInfectadoReportData> getReportGrupoInfectado(FiltrosExcelRequest filtrosRequest) {

        var neonatos = neonatoRepository.findAll(NeonatoSpecification.byFiltros(filtrosRequest, "infectado"));

        return neonatos.stream()
                .map(neonato -> {
                    System.out.println("Report Infectado IdNeonato: " + neonato.getIdNeonato());

                    return NeonatoMapper.convertToNeonatoGrupoInfectadoReportData(neonato, this, "infectado");
                })
                .toList();
    }


    public List<NeonatoGrupoInfectadoReportData> getReportGrupoColonizado(FiltrosExcelRequest filtrosRequest) {

        var neonatos = neonatoRepository.findAll(NeonatoSpecification.byFiltros(filtrosRequest, "colonizado"));

        return neonatos.stream()
                .map(neonato -> {
                    System.out.println("Report Colonizado IdNeonato: " + neonato.getIdNeonato());

                    return NeonatoMapper.convertToNeonatoGrupoInfectadoReportData(neonato, this, "colonizado");
                })
                .toList();
    }


    public List<Evento> buscarColetasInfeccao(List<Evento> eventos, String tipoRelatorio) {
        Predicate<Evento> isColetaBacteriana = evento ->
                evento.getTipoEvento() != null
                        && Objects.equals(evento.getTipoEvento().getIdTipoEvento(), 10L);

        // só isolados válidos (não nulos e não desconsiderados)
        Predicate<Evento> isIsoladoValido = evento ->
                evento.getIsoladoColeta() != null
                        && !evento.getIsoladoColeta().getDesconsiderarColeta();

        // filtro para infectado: exclui colonizações
        Predicate<Evento> filtroInfectado = evento -> {
            Long idEnt = evento.getEventoEntidade().getIdEntidade();
            // permite eventos sem entidade, ou com entidade NÃO em COLONIZACOES
            return idEnt == null || !COLONIZACOES.contains(idEnt);
        };

        // filtro para colonizado: só colonizações
        Predicate<Evento> filtroColonizado = evento -> {
            Long idEnt = evento.getEventoEntidade().getIdEntidade();
            return idEnt != null && COLONIZACOES.contains(idEnt);
        };

        return eventos.stream()
                .filter(isColetaBacteriana)
                .filter(isIsoladoValido)
                .filter(evento -> {
                    if ("infectado".equalsIgnoreCase(tipoRelatorio)) {
                        return filtroInfectado.test(evento);
                    } else if ("colonizado".equalsIgnoreCase(tipoRelatorio)) {
                        return filtroColonizado.test(evento);
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }


    public UsoProcedimentos getUsoProcedimentos(List<Evento> eventos) {
        var procedimentosDiasInfeccao = new UsoProcedimentos();
        procedimentosDiasInfeccao.setUso(eventos.isEmpty() ? 0 : 1);
        procedimentosDiasInfeccao.setDiasTotaisUso(eventos.size());
        return procedimentosDiasInfeccao;
    }


    public LinkedHashSet<LocalDate> getDatasInfeccoes(List<Evento> eventos) {
        return eventos.stream()
                .filter(evento -> evento.getTipoEvento().getIdTipoEvento().equals(10L) && evento.getTipoEvento().getIsActive())
                .map(Evento::getDataEvento)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
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

                var anfenicoisPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 23L);
                var anfenicoisPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 23L);

                var oxazolidinonasPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 24L);
                var oxazolidinonasPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 24L);

                var fosfonicosPre = getEventosPorClasseAntimicrobiano(eventosAteInfeccao, antimicrobianos, 25L);
                var fosfonicosPos = getEventosPorClasseAntimicrobiano(eventosAposInfeccao, antimicrobianos, 25L);

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

                medicamentos.setUsoAnfenicois(anfenicoisPos.isEmpty() && anfenicoisPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisAnfenicoisPrevio(contarEventosDatasUnicas(anfenicoisPre));
                medicamentos.setDiasTotaisAnfenicoisApos(contarEventosDatasUnicas(anfenicoisPos));

                medicamentos.setUsoOxazolidinonas(oxazolidinonasPos.isEmpty() && oxazolidinonasPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisOxazolidinonasPrevio(contarEventosDatasUnicas(oxazolidinonasPre));
                medicamentos.setDiasTotaisOxazolidinonasApos(contarEventosDatasUnicas(oxazolidinonasPos));

                medicamentos.setUsoFosfonicos(fosfonicosPos.isEmpty() && fosfonicosPre.isEmpty() ? 0 : 1);
                medicamentos.setDiasTotaisFosfonicosPrevio(contarEventosDatasUnicas(fosfonicosPre));
                medicamentos.setDiasTotaisFosfonicosApos(contarEventosDatasUnicas(fosfonicosPos));

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


    public List<IsoladosReportData> getReportIsolados(FiltrosExcelRequest filtrosRequest) {
        // 1. busca os neonatos e extrai todos os isolados já filtrados
        var neonatos = neonatoRepository.findAll(
                NeonatoSpecification.byFiltros(filtrosRequest, "infectado")
        );
        var isolados = getIsoladoColetaFromNeonatos(neonatos, filtrosRequest);

        // 2. computa o número
        Map<IsoladoColeta, Integer> sequenciaPorIsolado = computeInfectionSequence(isolados);
        Map<IsoladoColeta,Integer> sequenciaColonizacao = computeColonizationSequence(isolados);

        // 3. mapeia para o DTO, agora passando o nº da infecção
        return isolados.stream()
                .map(isolado -> {
                    int nInfec = sequenciaPorIsolado.getOrDefault(isolado, 0);
                    int seqColonizacao = sequenciaColonizacao.getOrDefault(isolado, 0);
                    return NeonatoMapper
                            .convertToIsoladosReportData(isolado, nInfec, seqColonizacao, this);
                })
                .toList();
    }

    /**
     * Para cada neonato, filtra apenas os isolados que são
     * de infecção (site_coleta & idEntidade ∉ {4,5,7,9}),
     * ordena por dataEvento e atribui 1,2,3...
     */
    private Map<IsoladoColeta, Integer> computeInfectionSequence(List<IsoladoColeta> isolados) {
        // Resultado mantido na ordem de ocorrência
        Map<IsoladoColeta, Integer> sequenceMap = new LinkedHashMap<>();

        isolados.stream()
                // filtra só o que é realmente infecção
                .filter(iso -> {
                    var ent = iso.getEvento().getEventoEntidade();
                    return "sitio_coleta".equals(ent.getTipoEntidade())
                            && !COLONIZACOES.contains(ent.getIdEntidade());
                })
                // agrupa por neonato
                .collect(Collectors.groupingBy(
                        iso -> iso.getEvento().getNeonato().getIdNeonato(),
                        LinkedHashMap::new, // mantém ordem de inserção por neonato
                        Collectors.toList()
                ))
                .forEach((neonatoId, lista) -> {
                    // ordena cronologicamente dentro de cada neonato
                    List<IsoladoColeta> ordenados = lista.stream()
                            .sorted(Comparator.comparing(iso -> iso.getEvento().getDataEvento()))
                            .toList();

                    Integer seq = 0;
                    LocalDate prevDate = null;
                    Long prevSite = null;

                    for (IsoladoColeta iso : ordenados) {
                        LocalDate data = iso.getEvento().getDataEvento();
                        Long site = iso.getEvento().getEventoEntidade().getIdEntidade();

                        // só incrementa seq quando mudar data ou sítio
                        if (prevDate == null
                                || !data.equals(prevDate)
                                || !Objects.equals(site, prevSite)) {
                            seq++;
                            prevDate = data;
                            prevSite = site;
                        }

                        sequenceMap.put(iso, seq);
                    }
                });

        return sequenceMap;
    }


    /**
     * Para cada neonato, filtra apenas os isolados que são
     * de colonização (site_coleta & idEntidade ∈ COLONIZACOES),
     * ordena por dataEvento e atribui 1,2,3... mantendo
     * o mesmo número quando data e sítio forem iguais.
     */
    private Map<IsoladoColeta, Integer> computeColonizationSequence(List<IsoladoColeta> isolados) {
        Map<IsoladoColeta, Integer> sequenceMap = new LinkedHashMap<>();

        isolados.stream()
                // filtra só o que é colonização
                .filter(iso -> {
                    var ent = iso.getEvento().getEventoEntidade();
                    return "sitio_coleta".equals(ent.getTipoEntidade())
                            && COLONIZACOES.contains(ent.getIdEntidade());
                })
                // agrupa por neonato
                .collect(Collectors.groupingBy(
                        iso -> iso.getEvento().getNeonato().getIdNeonato(),
                        LinkedHashMap::new,    // mantém ordem de inserção por neonato
                        Collectors.toList()
                ))
                .forEach((neonatoId, lista) -> {
                    // ordena cronologicamente dentro de cada neonato
                    List<IsoladoColeta> ordenados = lista.stream()
                            .sorted(Comparator.comparing(iso -> iso.getEvento().getDataEvento()))
                            .toList();

                    Integer seq = 0;
                    LocalDate prevDate = null;
                    Long prevSite = null;

                    for (IsoladoColeta iso : ordenados) {
                        LocalDate data = iso.getEvento().getDataEvento();
                        Long site = iso.getEvento().getEventoEntidade().getIdEntidade();

                        // incrementa seq só quando muda data OU sítio
                        if (prevDate == null
                                || !data.equals(prevDate)
                                || !Objects.equals(site, prevSite)) {
                            seq++;
                            prevDate = data;
                            prevSite = site;
                        }

                        sequenceMap.put(iso, seq);
                    }
                });

        return sequenceMap;
    }


    public List<IsoladoColeta> getIsoladoColetaFromNeonatos(List<Neonato> neonatos, FiltrosExcelRequest filtrosRequest) {
        return neonatos.stream()
                .flatMap(neonato -> neonato.getEventoList().stream())
                .map(Evento::getIsoladoColeta)
                .filter(isoladoColeta -> isoladoColeta != null)
                .filter(isoladoColeta -> !isoladoColeta.getDesconsiderarColeta())
                .filter(isoladoColeta -> isoladoColeta.getEvento().getIsActive())
                .filter(isoladoColeta -> filtrarSitioColeta(isoladoColeta, filtrosRequest))
                .filter(isoladoColeta -> filtrarMecanismoResistencia(isoladoColeta, filtrosRequest))
                .filter(isoladoColeta -> filtrarPerfilResistencia(isoladoColeta, filtrosRequest))
                .toList();
    }


    private boolean filtrarSitioColeta(IsoladoColeta isoladoColeta, FiltrosExcelRequest filtros) {
        // Se não houver filtro de 'sitioColeta', deixa passar
        if (filtros.sitioColeta() == null || filtros.sitioColeta().isEmpty()) {
            return true;
        }
        // Precisamos verificar se o EventoEntidade é do tipo "sitio_coleta" e se o idEntidade está no filtro
        var eventoEntidade = isoladoColeta.getEvento().getEventoEntidade();
        if (eventoEntidade == null) {
            return false;
        }
        if (!"sitio_coleta".equals(eventoEntidade.getTipoEntidade())) {
            return false;
        }
        // Se o ID de entidade está na lista
        return filtros.sitioColeta().contains(eventoEntidade.getIdEntidade().intValue());
    }


    private boolean filtrarMecanismoResistencia(IsoladoColeta isoladoColeta, FiltrosExcelRequest filtros) {
        // Se não houver filtro de 'mecanismoResistencia', deixa passar
        if (filtros.mecanismoResistencia() == null || filtros.mecanismoResistencia().isEmpty()) {
            return true;
        }
        // Verifica o mecanismoResistenciaMicroorganismo
        var mecanismo = isoladoColeta.getMecanismoResistenciaMicroorganismo();
        if (mecanismo == null) {
            return false;
        }
        return filtros.mecanismoResistencia().contains(mecanismo.getIdMecanismoResistenciaMicroorganismo().intValue());
    }


    private boolean filtrarPerfilResistencia(IsoladoColeta isoladoColeta, FiltrosExcelRequest filtros) {
        // Se não houver filtro de 'perfilResistencia', deixa passar
        if (filtros.perfilResistencia() == null || filtros.perfilResistencia().isEmpty()) {
            return true;
        }
        // Verifica o perfilResistenciaMicroorganismo
        var perfil = isoladoColeta.getPerfilResistenciaMicroorganismo();
        if (perfil == null) {
            return false;
        }
        return filtros.perfilResistencia().contains(perfil.getIdPerfilResistenciaMicroorganismo().intValue());
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
                .map(isolado -> isolado.getResistenciaMicroorganismo().getCodigo())
                .findFirst()
                .orElse(null);
    }


    public List<ProcedimentosEpisodioInfeccao> getProcedimentosEpisodioInfeccao(List<Evento> coletasInfeccao, ProcedimentosEpisodioContext procedimentos) {
        var agrupadosSemOrdem = coletasInfeccao.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        evento -> Pair.of(evento.getDataEvento(), evento.getEventoEntidade().getIdEntidade())
                ));

        var agrupados = agrupadosSemOrdem.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().getFirst()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));


        var procedimentosEpisodioInfeccaoList = new ArrayList<ProcedimentosEpisodioInfeccao>();

        for (var entry : agrupados.entrySet()) {
            var eventos = entry.getValue();
            if (eventos.isEmpty()) continue;

            var dataInfeccao = entry.getKey().getFirst();
            var idEntidade = entry.getKey().getSecond();

            var procedimentosInfeccao = new ProcedimentosEpisodioInfeccao();
            procedimentosInfeccao.setData(DateUtil.LocalDateToDateBR(dataInfeccao));
            procedimentosInfeccao.setSitio(getCodigoCadastro("sitio_coleta", "id_sitio_coleta", idEntidade));

            // Preenchimento comum
            procedimentosInfeccao.setDiasCirurgiaAte(getEventosAteInfeccao(procedimentos.cirurgiasList, dataInfeccao).size());
            procedimentosInfeccao.setDiasCirurgiaApos(getEventosAposInfeccao(procedimentos.cirurgiasList, dataInfeccao).size());

            procedimentosInfeccao.setDiasFlebotomiaAte(getEventosAteInfeccao(procedimentos.flebotomiaList, dataInfeccao).size());
            procedimentosInfeccao.setDiasFlebotomiaApos(getEventosAposInfeccao(procedimentos.flebotomiaList, dataInfeccao).size());

            procedimentosInfeccao.setDiasCvuAte(getEventosAteInfeccao(procedimentos.cvuList, dataInfeccao).size());
            procedimentosInfeccao.setDiasCvuApos(getEventosAposInfeccao(procedimentos.cvuList, dataInfeccao).size());

            procedimentosInfeccao.setDiasPiccAte(getEventosAteInfeccao(procedimentos.piccList, dataInfeccao).size());
            procedimentosInfeccao.setDiasPiccApos(getEventosAposInfeccao(procedimentos.piccList, dataInfeccao).size());

            procedimentosInfeccao.setDiasEntubacaoAte(getEventosAteInfeccao(procedimentos.entubacaoList, dataInfeccao).size());
            procedimentosInfeccao.setDiasEntubacaoApos(getEventosAposInfeccao(procedimentos.entubacaoList, dataInfeccao).size());

            procedimentosInfeccao.setDiasSondaVesicalAte(getEventosAteInfeccao(procedimentos.sondaVesicalList, dataInfeccao).size());
            procedimentosInfeccao.setDiasSondaVesicalApos(getEventosAposInfeccao(procedimentos.sondaVesicalList, dataInfeccao).size());

            procedimentosInfeccao.setDiasNutricaoParenteralAte(getEventosAteInfeccao(procedimentos.nutricaoParenteralList, dataInfeccao).size());
            procedimentosInfeccao.setDiasNutricaoParenteralApos(getEventosAposInfeccao(procedimentos.nutricaoParenteralList, dataInfeccao).size());

            procedimentosInfeccao.setDiasDrenoAte(getEventosAteInfeccao(procedimentos.drenoList, dataInfeccao).size());
            procedimentosInfeccao.setDiasDrenoApos(getEventosAposInfeccao(procedimentos.drenoList, dataInfeccao).size());

            procedimentosInfeccao.setDiasIntracathAte(getEventosAteInfeccao(procedimentos.intracathList, dataInfeccao).size());
            procedimentosInfeccao.setDiasIntracathApos(getEventosAposInfeccao(procedimentos.intracathList, dataInfeccao).size());

            procedimentosInfeccao.setDiasCvcDuploLumenAte(getEventosAteInfeccao(procedimentos.cvcDuploLumenList, dataInfeccao).size());
            procedimentosInfeccao.setDiasCvcDuploLumenApos(getEventosAposInfeccao(procedimentos.cvcDuploLumenList, dataInfeccao).size());

            procedimentosInfeccao.setInfeccaoMista(eventos.size() > 1 ? 1 : 0);

            // Preenche campos para 1º evento
            var ev1 = eventos.get(0);
            procedimentosInfeccao.setMicroorganismo(ev1.getIsoladoColeta().getMicroorganismo().getCodigo());
            procedimentosInfeccao.setClassificacaoMicroorganismo(ev1.getIsoladoColeta().getMicroorganismo().getClassificacaoMicroorganismo().getCodigo());
            procedimentosInfeccao.setPerfilResistenciaMicroorganismo(ev1.getIsoladoColeta().getPerfilResistenciaMicroorganismo().getCodigo());
            procedimentosInfeccao.setMecanismoResistenciaMicroorganismo(ev1.getIsoladoColeta().getPerfilResistenciaMicroorganismo().getCodigo());

            // Preenche campos do segundo microorganismo (se houver)
            if (eventos.size() > 1) {
                var ev2 = eventos.get(1);
                procedimentosInfeccao.setMicroorganismo2(ev2.getIsoladoColeta().getMicroorganismo().getCodigo());
                procedimentosInfeccao.setClassificacaoMicroorganismo2(ev2.getIsoladoColeta().getMicroorganismo().getClassificacaoMicroorganismo().getCodigo());
                procedimentosInfeccao.setPerfilResistenciaMicroorganismo2(ev2.getIsoladoColeta().getPerfilResistenciaMicroorganismo().getCodigo());
                procedimentosInfeccao.setMecanismoResistenciaMicroorganismo2(ev2.getIsoladoColeta().getPerfilResistenciaMicroorganismo().getCodigo());
            }

            procedimentosEpisodioInfeccaoList.add(procedimentosInfeccao);
        }

        return procedimentosEpisodioInfeccaoList;
    }


}
