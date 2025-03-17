package com.roderly.pesquisaneonatos.neonato.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.causa_obito.model.CausaObito;
import com.roderly.pesquisaneonatos.cadastros_gerais.idade_gestacional.model.IdadeGestacional;
import com.roderly.pesquisaneonatos.cadastros_gerais.local_nascimento.model.LocalNascimento;
import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.model.MotivoInternacao;
import com.roderly.pesquisaneonatos.cadastros_gerais.peso_nascimento.model.PesoNascimento;
import com.roderly.pesquisaneonatos.cadastros_gerais.rotura_membrana.model.RoturaMembrana;
import com.roderly.pesquisaneonatos.cadastros_gerais.sexo.model.Sexo;
import com.roderly.pesquisaneonatos.cadastros_gerais.sitio_malformacao.model.SitioMalformacao;
import com.roderly.pesquisaneonatos.cadastros_gerais.tipo_parto.model.TipoParto;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.neonato.dto.request.NeonatoAusenciaUTIRequest;
import com.roderly.pesquisaneonatos.neonato.dto.request.NeonatoRequest;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoAusenciaUTIResponse;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoListResponse;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoResponse;
import com.roderly.pesquisaneonatos.neonato.excel.IsoladosReportData;
import com.roderly.pesquisaneonatos.neonato.excel.NeonatoGrupoControleReportData;
import com.roderly.pesquisaneonatos.neonato.excel.NeonatoGrupoInfectadoReportData;
import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import com.roderly.pesquisaneonatos.neonato.model.NeonatoAusenciaUTI;
import com.roderly.pesquisaneonatos.neonato.model.NeonatoSitioMalformacao;
import com.roderly.pesquisaneonatos.neonato.service.NeonatoService;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.ClasseAntimicrobianoCountProjection;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.EventoCountProjection;
import com.roderly.pesquisaneonatos.prontuario.model.IsoladoColeta;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class NeonatoMapper {

    public static int numeroAnalisesInfeccoes = 10;

    public static Neonato convertNeonatoRequestToNeonato(NeonatoRequest request) {

        var sexo = request.idSexo() != null ? new Sexo(request.idSexo()) : null;
        var pesoNascimento = request.idPesoNascimento() != null ? new PesoNascimento(request.idPesoNascimento()) : null;
        var motivoInternacao = request.idMotivoInternacao() != null ? new MotivoInternacao(request.idMotivoInternacao()) : null;
        var localNascimento = request.idLocalNascimento() != null ? new LocalNascimento(request.idLocalNascimento()) : null;
        var idadeGestacional = request.idIdadeGestacional() != null ? new IdadeGestacional(request.idIdadeGestacional()) : null;
        var tipoParto = request.idTipoParto() != null ? new TipoParto(request.idTipoParto()) : null;
        var roturaMembrana = request.idRoturaMembrana() != null ? new RoturaMembrana(request.idRoturaMembrana()) : null;
        var causaObito = request.idCausaObito() != null ? new CausaObito(request.idCausaObito()) : null;


        var neonato = new Neonato();
        neonato.setIdNeonato(request.idNeonato());
        neonato.setNomeMae(request.nomeMae());
        neonato.setProntuario(request.prontuario());
        neonato.setDataNascimento(request.dataNascimento());
        neonato.setDataInternacao(request.dataInternacao());
        neonato.setDataDesfecho(request.dataDesfecho());
        neonato.setObito(request.obito());
        neonato.setGemelar(request.gemelar());
        neonato.setRiscoInfeccio(request.riscoInfeccioso());
        neonato.setSepseClinica(request.sepseClinica());
        neonato.setApgar1(request.apgar1());
        neonato.setApgar5(request.apgar5());
        neonato.setPesoGramas(request.pesoGramas());
        neonato.setSexo(sexo);
        neonato.setPesoNascimento(pesoNascimento);
        neonato.setMotivoInternacao(motivoInternacao);
        neonato.setLocalNascimento(localNascimento);
        neonato.setIdadeGestacional(idadeGestacional);
        neonato.setTipoParto(tipoParto);
        neonato.setRoturaMembrana(roturaMembrana);
        neonato.setCausaObito(causaObito);

        return neonato;
    }


    public static NeonatoAusenciaUTI neonatoAusenciaUTIrequestToNeonatoAusenciaUTI(NeonatoAusenciaUTIRequest request, Neonato neonato) {

        var ausenciaUti = new NeonatoAusenciaUTI();
        ausenciaUti.setIdNeonatoAusenciaUti(request.idNeonatosAusenciaUti());
        ausenciaUti.setNeonato(neonato);
        ausenciaUti.setDataSaidaUti(request.dataSaidaUti());
        ausenciaUti.setDataRetornoUti(request.dataRetornoUti());

        return ausenciaUti;
    }


    public static NeonatoSitioMalformacao neonatoToNeonatoSitioMalformacao(Long idSitioMalformacao, Neonato neonato) {

        var sitioMalformacao = new SitioMalformacao(idSitioMalformacao);

        var neonatoSitioMalformacao = new NeonatoSitioMalformacao();
        neonatoSitioMalformacao.setNeonato(neonato);
        neonatoSitioMalformacao.setSitioMalformacao(sitioMalformacao);

        return neonatoSitioMalformacao;
    }

    public static NeonatoResponse convertNeonatoToNeonatoResponse(Neonato neonato) {

        var sitiosMalformacao = neonato.getNeonatoMalformacaoList().stream()
                .map(neonatoSitioMalformacao -> neonatoSitioMalformacao.getSitioMalformacao().getIdSitioMalformacao())
                .toList();

        var ausenciasUti = neonato.getNeonatoAusenciaUtiList().stream()
                .map(ausenciaUTI -> new NeonatoAusenciaUTIResponse(
                        ausenciaUTI.getIdNeonatoAusenciaUti(),
                        ausenciaUTI.getDataSaidaUti(),
                        ausenciaUTI.getDataRetornoUti()
                ))
                .toList();

        return new NeonatoResponse(
                neonato.getIdNeonato(),
                neonato.getNomeMae(),
                neonato.getProntuario(),
                neonato.getDataNascimento(),
                neonato.getDataInternacao(),
                neonato.getDataDesfecho(),
                neonato.getObito(),
                neonato.getGemelar(),
                neonato.getApgar1(),
                neonato.getApgar5(),
                neonato.getPesoGramas(),
                neonato.getSexo() != null ? neonato.getSexo().getIdSexo() : null,
                neonato.getPesoNascimento() != null ? neonato.getPesoNascimento().getIdPesoNascimento() : null,
                neonato.getMotivoInternacao() != null ? neonato.getMotivoInternacao().getIdMotivoInternacao() : null,
                neonato.getLocalNascimento() != null ? neonato.getLocalNascimento().getIdLocalNascimento() : null,
                neonato.getIdadeGestacional() != null ? neonato.getIdadeGestacional().getIdIdadeGestacional() : null,
                neonato.getTipoParto() != null ? neonato.getTipoParto().getIdTipoParto() : null,
                neonato.getRoturaMembrana() != null ? neonato.getRoturaMembrana().getIdRoturaMembrana() : null,
                sitiosMalformacao,
                neonato.getCausaObito() != null ? neonato.getCausaObito().getIdCausaObito() : null,
                neonato.getRiscoInfeccio(),
                neonato.getSepseClinica(),
                ausenciasUti
        );
    }


    public static NeonatoListResponse convertNeonatoToNeonatoListResponse(Neonato neonato) {

        var dataNascimento = neonato.getDataNascimento() != null ? DateUtil.LocalDateToDateBR(neonato.getDataNascimento()) : null;
        var dataInternacao = neonato.getDataInternacao() != null ? DateUtil.LocalDateToDateBR(neonato.getDataInternacao()) : null;
        var dataDesfecho = neonato.getDataDesfecho() != null ? DateUtil.LocalDateToDateBR(neonato.getDataDesfecho()) : null;

        return new NeonatoListResponse(
                neonato.getIdNeonato(),
                neonato.getNomeMae(),
                neonato.getProntuario(),
                dataNascimento,
                dataInternacao,
                dataDesfecho
        );
    }


    public static NeonatoGrupoControleReportData convertToNeonatoGrupoControleReportData(Neonato neonato, Long cirurgiaNeonato, List<EventoCountProjection> eventos, Long diasUsoATB, Long diasUsoATF, List<ClasseAntimicrobianoCountProjection> classeAntimicrobiano, NeonatoService neonatoService) {

        var dataNascimento = neonato.getDataNascimento() != null ? DateUtil.LocalDateToDateBR(neonato.getDataNascimento()) : null;
        var dataInternacao = neonato.getDataInternacao() != null ? DateUtil.LocalDateToDateBR(neonato.getDataInternacao()) : null;
        var dataDesfecho = neonato.getDataDesfecho() != null ? DateUtil.LocalDateToDateBR(neonato.getDataDesfecho()) : null;
        var ano = neonato.getDataInternacao() != null ? neonato.getDataInternacao().getYear() : null;

        var diasForaUti = neonatoService.calcularDiasForaUTI(neonato.getNeonatoAusenciaUtiList());
        var diasUti = DateUtil.calcularDiferencaDias(neonato.getDataInternacao(), neonato.getDataDesfecho()).orElse(0L);
        var diasInternacao = diasUti - diasForaUti + 1;

        var flebotomia = neonatoService.getEventoTipoDias(eventos, 8L);
        var cvu = neonatoService.getEventoTipoDias(eventos, 7L);
        var picc = neonatoService.getEventoTipoDias(eventos, 6L);
        var intubacao = neonatoService.getEventoTipoDias(eventos, 3L);
        var sonda = neonatoService.getEventoTipoDias(eventos, 4L);
        var nutricaoParenteral = neonatoService.getEventoTipoDias(eventos, 5L);
        var dreno = neonatoService.getEventoTipoDias(eventos, 2L);

        var aminoglicosideos = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 1L);
        var ansamicinas = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 2L);
        var betalactamicos = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 3L);
        var carbapenemicos = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 4L);
        var cefalosporinasPrimeiraGeracao = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 5L);
        var cefalosporinasSegundaGeracao = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 6L);
        var cefalosporinasTerceiraGeracao = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 7L);
        var cefalosporinasQuartaGeracao = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 8L);
        var cefalosporinasQuintaGeracao = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 9L);
        var glicilciclinas = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 10L);
        var glicopeptideos = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 11L);
        var inibidoresDeFolato = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 12L);
        var lincosamidas = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 13L);
        var macrolideos = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 14L);
        var nitrofuranicos = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 15L);
        var nitroimidazois = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 16L);
        var polimixinas = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 17L);
        var quinolonas = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 18L);
        var tetraciclinas = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 19L);
        var azois = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 20L);
        var equinocandinas = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 21L);
        var polienos = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 22L);
        var anfenicois = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 23L);
        var oxazolidinonas = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 24L);
        var fosfonicos = neonatoService.getClasseAnimicrobianoDias(classeAntimicrobiano, 25L);

        var report = new NeonatoGrupoControleReportData();

        report.setAno(ano);
        report.setProntuario(neonato.getProntuario());
        report.setDataNascimento(dataNascimento);
        report.setDataInternacao(dataInternacao);
        report.setDataDesfecho(dataDesfecho);
        report.setDiasInternacao(diasInternacao);
        report.setObito(neonato.getObito() ? 1L : 0L);
        report.setLocalNascimentoCodigo(neonato.getLocalNascimento() != null ? neonato.getLocalNascimento().getCodigo() : null);
        report.setSexoCodigo(neonato.getSexo() != null ? neonato.getSexo().getCodigo() : null);
        report.setMotivoInternacaoCodigo(neonato.getMotivoInternacao() != null ? neonato.getMotivoInternacao().getSistemaFisiologico().getCodigo() : null);
        report.setPesoGramas(neonato.getPesoGramas());
        report.setPesoNascimentoCodigo(neonato.getPesoNascimento() != null ? neonato.getPesoNascimento().getCodigo() : null);
        report.setIdadeGestacionalCodigo(neonato.getIdadeGestacional() != null ? neonato.getIdadeGestacional().getCodigo() : null);
        report.setTipoPartoCodigo(neonato.getTipoParto() != null ? neonato.getTipoParto().getCodigo() : null);
        report.setRoturaMembranaCodigo(neonato.getRoturaMembrana() != null ? neonato.getRoturaMembrana().getCodigo() : null);
        report.setMalformacao(neonato.getNeonatoMalformacaoList().isEmpty() ? 0L : 1L);
        report.setCirurgia(cirurgiaNeonato);

        report.setFlebotomia(flebotomia.evento());
        report.setDiasFlebotomia(flebotomia.diasEvento());
        report.setCVU(cvu.evento());
        report.setDiasCVU(cvu.diasEvento());
        report.setPICC(picc.evento());
        report.setDiasPICC(picc.diasEvento());
        report.setIntubacao(intubacao.evento());
        report.setDiasIntubacao(intubacao.diasEvento());
        report.setSonda(sonda.evento());
        report.setDiasSonda(sonda.diasEvento());
        report.setNutricaoParenteral(nutricaoParenteral.evento());
        report.setDiasNutricaoParenteral(nutricaoParenteral.diasEvento());
        report.setDreno(dreno.evento());
        report.setDiasDreno(dreno.diasEvento());

        report.setUsoATB(diasUsoATB > 0 ? 1L : 0L);
        report.setDiasUsoATB(diasUsoATB);
        report.setAminoglicosideos(aminoglicosideos.evento());
        report.setDiasAminoglicosideos(aminoglicosideos.diasEvento());
        report.setAnsamicinas(ansamicinas.evento());
        report.setDiasAnsamicinas(ansamicinas.diasEvento());
        report.setBetalactamicos(betalactamicos.evento());
        report.setDiasBetalactamicos(betalactamicos.diasEvento());
        report.setCarbapenemicos(carbapenemicos.evento());
        report.setDiasCarbapenemicos(carbapenemicos.diasEvento());
        report.setCefalosporinasPrimeiraGeracao(cefalosporinasPrimeiraGeracao.diasEvento());
        report.setDiasCefalosporinasPrimeiraGeracao(cefalosporinasPrimeiraGeracao.evento());
        report.setCefalosporinasSegundaGeracao(cefalosporinasSegundaGeracao.evento());
        report.setDiasCefalosporinasSegundaGeracao(cefalosporinasSegundaGeracao.diasEvento());
        report.setCefalosporinasTerceiraGeracao(cefalosporinasTerceiraGeracao.evento());
        report.setDiasCefalosporinasTerceiraGeracao(cefalosporinasTerceiraGeracao.diasEvento());
        report.setCefalosporinasQuartaGeracao(cefalosporinasQuartaGeracao.evento());
        report.setDiasCefalosporinasQuartaGeracao(cefalosporinasQuartaGeracao.diasEvento());
        report.setCefalosporinasQuintaGeracao(cefalosporinasQuintaGeracao.evento());
        report.setDiasCefalosporinasQuintaGeracao(cefalosporinasQuintaGeracao.diasEvento());
        report.setGlicilciclinas(glicilciclinas.evento());
        report.setDiasGlicilciclinas(glicilciclinas.diasEvento());
        report.setGlicopeptideos(glicopeptideos.evento());
        report.setDiasGlicopeptideos(glicopeptideos.diasEvento());
        report.setInibidoresDeFolato(inibidoresDeFolato.evento());
        report.setDiasInibidoresDeFolato(inibidoresDeFolato.diasEvento());
        report.setLincosamidas(lincosamidas.evento());
        report.setDiasLincosamidas(lincosamidas.diasEvento());
        report.setMacrolideos(macrolideos.evento());
        report.setDiasMacrolideos(macrolideos.diasEvento());
        report.setNitrofuranicos(nitrofuranicos.evento());
        report.setDiasNitrofuranicos(nitrofuranicos.diasEvento());
        report.setNitroimidazolicos(nitroimidazois.evento());
        report.setDiasNitroimidazolicos(nitroimidazois.diasEvento());
        report.setPolimixinas(polimixinas.evento());
        report.setDiasPolimixinas(polimixinas.diasEvento());
        report.setQuinolonas(quinolonas.evento());
        report.setDiasQuinolonas(quinolonas.diasEvento());
        report.setTetraciclinas(tetraciclinas.evento());
        report.setDiasTetraciclinas(tetraciclinas.diasEvento());
        report.setAnfenicois(anfenicois.evento());
        report.setDiasAnfenicois(anfenicois.diasEvento());
        report.setOxazolidinonas(oxazolidinonas.evento());
        report.setDiasOxazolidinonas(oxazolidinonas.diasEvento());
        report.setFosfonicos(fosfonicos.evento());
        report.setDiasFosfonicos(fosfonicos.diasEvento());


        report.setUsoATF(diasUsoATF > 0 ? 1L : 0L);
        report.setDiasUsoATF(diasUsoATF);
        report.setAzois(azois.evento());
        report.setDiasAzois(azois.diasEvento());
        report.setEquinocandinas(equinocandinas.evento());
        report.setDiasEquinocandinas(equinocandinas.diasEvento());
        report.setPolienos(polienos.evento());
        report.setDiasPolienos(polienos.diasEvento());

        report.setMotivoObito(neonato.getCausaObito() != null ? neonato.getCausaObito().getCodigo() : null);

        return report;
    }


    public static NeonatoGrupoInfectadoReportData convertToNeonatoGrupoInfectadoReportData(Neonato neonato, NeonatoService neonatoService) {

        var dataNascimento = neonato.getDataNascimento() != null ? DateUtil.LocalDateToDateBR(neonato.getDataNascimento()) : null;
        var dataInternacao = neonato.getDataInternacao() != null ? DateUtil.LocalDateToDateBR(neonato.getDataInternacao()) : null;
        var dataDesfecho = neonato.getDataDesfecho() != null ? DateUtil.LocalDateToDateBR(neonato.getDataDesfecho()) : null;
        var ano = neonato.getDataInternacao() != null ? neonato.getDataInternacao().getYear() : null;
        var eventos = neonato.getEventoList().stream().filter(evento -> evento.getIsActive()).toList();

        var diasForaUti = neonatoService.calcularDiasForaUTI(neonato.getNeonatoAusenciaUtiList());
        var diasUti = DateUtil.calcularDiferencaDias(neonato.getDataInternacao(), neonato.getDataDesfecho()).orElse(0L);
        var diasInternacao = diasUti - diasForaUti + 1;

        var malformacaoList = neonato.getNeonatoMalformacaoList();
        var cirurgiasList = neonatoService.filtrarListaEventosPorTipo(eventos, 9L);
        var flebotomiaList = neonatoService.filtrarListaEventosPorTipo(eventos, 8L);
        var cvuList = neonatoService.filtrarListaEventosPorTipo(eventos, 7L);
        var intracathList = neonatoService.filtrarListaEventosPorTipo(eventos, 11L);
        var cvcDuploLumenList = neonatoService.filtrarListaEventosPorTipo(eventos, 12L);
        var piccList = neonatoService.filtrarListaEventosPorTipo(eventos, 6L);
        var entubacaoList = neonatoService.filtrarListaEventosPorTipo(eventos, 3L);
        var sondaVesicalList = neonatoService.filtrarListaEventosPorTipo(eventos, 4L);
        var nutricaoParenteralList = neonatoService.filtrarListaEventosPorTipo(eventos, 5L);
        var drenoList = neonatoService.filtrarListaEventosPorTipo(eventos, 2L);
        var medicacaoList = neonatoService.filtrarListaEventosPorTipo(eventos, 1L);

        var coletasInfeccao = neonatoService.buscarColetasInfeccao(eventos);
        var datasInfeccao = new ArrayList<>(neonatoService.getDatasInfeccoes(coletasInfeccao));

        var coletasSangue = neonatoService.listaEpisodiosColeta(neonatoService.filtrarListaColetasPorLocal(eventos, 1L));
        var coletasLCR = neonatoService.listaEpisodiosColeta(neonatoService.filtrarListaColetasPorLocal(eventos, 2L));
        var coletasSecrecaoOcular = neonatoService.listaEpisodiosColeta(neonatoService.filtrarListaColetasPorLocal(eventos, 3L));
        var coletasSwabAnal = neonatoService.listaEpisodiosColeta(neonatoService.filtrarListaColetasPorLocal(eventos, 4L));
        var coletasPontaCateter = neonatoService.listaEpisodiosColeta(neonatoService.filtrarListaColetasPorLocal(eventos, 5L));
        var coletasUrina = neonatoService.listaEpisodiosColeta(neonatoService.filtrarListaColetasPorLocal(eventos, 6L));
        var coletasSecrecaoPulmonar = neonatoService.listaEpisodiosColeta(neonatoService.filtrarListaColetasPorLocal(eventos, 7L));
        var coletasLiquidoAscitico = neonatoService.listaEpisodiosColeta(neonatoService.filtrarListaColetasPorLocal(eventos, 8L));
        var coletaLiquidoPleural = neonatoService.listaEpisodiosColeta(neonatoService.filtrarListaColetasPorLocal(eventos, 9L));

        var report = new NeonatoGrupoInfectadoReportData();

        report.setAno(ano);
        report.setProntuario(neonato.getProntuario());
        //report.setPaciente(neonato.getNomeMae());
        report.setDataNascimento(dataNascimento);
        report.setDataInternacao(dataInternacao);
        report.setDataDesfecho(dataDesfecho);
        report.setDiasInternacao(diasInternacao);
        report.setObito(neonato.getObito() ? 1L : 0L);
        report.setLocalNascimentoCodigo(neonato.getLocalNascimento() != null ? neonato.getLocalNascimento().getCodigo() : null);
        report.setMotivoInternacaoCodigo(neonato.getMotivoInternacao() != null ? neonato.getMotivoInternacao().getSistemaFisiologico().getCodigo() : null);
        report.setSexoCodigo(neonato.getSexo() != null ? neonato.getSexo().getCodigo() : null);
        report.setPesoGramas(neonato.getPesoGramas());
        report.setPesoNascimentoCodigo(neonato.getPesoNascimento() != null ? neonato.getPesoNascimento().getCodigo() : null);
        report.setIdadeGestacionalCodigo(neonato.getIdadeGestacional() != null ? neonato.getIdadeGestacional().getCodigo() : null);
        report.setTipoPartoCodigo(neonato.getTipoParto() != null ? neonato.getTipoParto().getCodigo() : null);
        report.setRoturaMembranaCodigo(neonato.getRoturaMembrana() != null ? neonato.getRoturaMembrana().getCodigo() : null);
        report.setApgar1(neonato.getApgar1());
        report.setApgar5(neonato.getApgar5());
        report.setMalformacao(malformacaoList.isEmpty() ? 0L : 1L);
        report.setSitioMalformacoes(neonatoService.getColunasMalformacao(malformacaoList));
        report.setCirurgia(neonatoService.getProcedimentosDiasInfeccao(cirurgiasList, datasInfeccao));
        report.setColunasCirurgia(neonatoService.getColunasCirurgias(cirurgiasList));
        report.setFlebotomia(neonatoService.getProcedimentosDiasInfeccao(flebotomiaList, datasInfeccao));
        report.setCvu(neonatoService.getProcedimentosDiasInfeccao(cvuList, datasInfeccao));
        report.setPicc(neonatoService.getProcedimentosDiasInfeccao(piccList, datasInfeccao));
        report.setEntubacao(neonatoService.getProcedimentosDiasInfeccao(entubacaoList, datasInfeccao));
        report.setSondaVesical(neonatoService.getProcedimentosDiasInfeccao(sondaVesicalList, datasInfeccao));
        report.setNutricaoParenteral(neonatoService.getProcedimentosDiasInfeccao(nutricaoParenteralList, datasInfeccao));
        report.setDreno(neonatoService.getProcedimentosDiasInfeccao(drenoList, datasInfeccao));
        report.setIntracath(neonatoService.getProcedimentosDiasInfeccao(intracathList, datasInfeccao));
        report.setCvcDuploLumen(neonatoService.getProcedimentosDiasInfeccao(cvcDuploLumenList, datasInfeccao));
        //report.setRiscoInfeccioso(neonato.getRiscoInfeccio() ? 1 : 0);
        //report.setSepseClinica(neonato.getSepseClinica() ? 1 : 0);
        report.setColetaSangue(coletasSangue);
        report.setColetaSecOcular(coletasSecrecaoOcular);
        report.setColetaLiqPleural(coletaLiquidoPleural);
        report.setColetaLCR(coletasLCR);
        report.setColetaLiqAscitico(coletasLiquidoAscitico);
        report.setColetaUrina(coletasUrina);
        report.setColetaSecrecaoPulmonar(coletasSecrecaoPulmonar);
        report.setColetaSwabAnal(coletasSwabAnal);
        report.setColetaPontaCateter(coletasPontaCateter);

        report.setMedicamentosEpisodiosInfeccao(neonatoService.getMedicamentosEpisodiosInfeccao(medicacaoList, datasInfeccao));

        return report;
    }


    public static IsoladosReportData convertToIsoladosReportData(IsoladoColeta isoladoColeta, NeonatoService neonatoService) {

        var evento = isoladoColeta.getEvento();
        var neonato = evento.getNeonato();
        var antibiogramas = isoladoColeta.getAntibiogramasIsolado();
        var dataNascimento = neonato.getDataNascimento() != null ? DateUtil.LocalDateToDateBR(neonato.getDataNascimento()) : null;
        var dataInternacao = neonato.getDataInternacao() != null ? DateUtil.LocalDateToDateBR(neonato.getDataInternacao()) : null;
        var dataDesfecho = neonato.getDataDesfecho() != null ? DateUtil.LocalDateToDateBR(neonato.getDataDesfecho()) : null;
        var ano = neonato.getDataInternacao() != null ? neonato.getDataInternacao().getYear() : null;

        var diasForaUti = neonatoService.calcularDiasForaUTI(neonato.getNeonatoAusenciaUtiList());
        var diasUti = DateUtil.calcularDiferencaDias(neonato.getDataInternacao(), neonato.getDataDesfecho()).orElse(0L);
        var diasInternacao = diasUti - diasForaUti + 1;

        var report = new IsoladosReportData();

        report.setAno(ano);
        report.setProntuario(neonato.getProntuario());
        //report.setPaciente(neonato.getNomeMae());
        report.setDataNascimento(dataNascimento);
        report.setDataInternacao(dataInternacao);
        report.setDataDesfecho(dataDesfecho);
        report.setDiasInternacao(diasInternacao);
        report.setObito(neonato.getObito() ? 1L : 0L);
        report.setLocalNascimentoCodigo(neonato.getLocalNascimento() != null ? neonato.getLocalNascimento().getCodigo() : null);
        report.setMotivoInternacaoCodigo(neonato.getMotivoInternacao() != null ? neonato.getMotivoInternacao().getSistemaFisiologico().getCodigo() : null);
        report.setSexoCodigo(neonato.getSexo() != null ? neonato.getSexo().getCodigo() : null);
        report.setPesoGramas(neonato.getPesoGramas());
        report.setPesoNascimentoCodigo(neonato.getPesoNascimento() != null ? neonato.getPesoNascimento().getCodigo() : null);
        report.setIdadeGestacionalCodigo(neonato.getIdadeGestacional() != null ? neonato.getIdadeGestacional().getCodigo() : null);
        report.setTipoPartoCodigo(neonato.getTipoParto() != null ? neonato.getTipoParto().getCodigo() : null);
        report.setRoturaMembranaCodigo(neonato.getRoturaMembrana() != null ? neonato.getRoturaMembrana().getCodigo() : null);
        report.setApgar1(neonato.getApgar1());
        report.setApgar5(neonato.getApgar5());

        report.setDataColeta(DateUtil.LocalDateToDateBR(isoladoColeta.getEvento().getDataEvento()));
        report.setSitioColetaCodigo(neonatoService.getCodigoCadastro("sitio_coleta", "id_sitio_coleta", evento.getEventoEntidade().getIdEntidade()));
        report.setAgenteCodigo(isoladoColeta.getMicroorganismo().getCodigo());
        report.setClassificacaoAgenteCodigo(isoladoColeta.getMicroorganismo().getClassificacaoMicroorganismo().getCodigo());
        report.setResistencia3OuMaisDrogas(neonatoService.verificarNResistencia(antibiogramas));
        report.setMecanismoResistencia(isoladoColeta.getMecanismoResistenciaMicroorganismo().getCodigo());
        report.setPerfilResistencia(isoladoColeta.getPerfilResistenciaMicroorganismo().getCodigo());

        report.setResistenciaAminoglicosideos(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 1L));
        report.setResistenciaAnsamicinas(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 2L));
        report.setResistenciaBetalactamicos(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 3L));
        report.setResistenciaCarbapenemicos(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 4L));
        report.setResistenciaCefalosporinasPrimeiraGeracao(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 5L));
        report.setResistenciaCefalosporinasSegundaGeracao(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 6L));
        report.setResistenciaCefalosporinasTerceiraGeracao(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 7L));
        report.setResistenciaCefalosporinasQuartaGeracao(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 8L));
        report.setResistenciaCefalosporinasQuintaGeracao(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 9L));
        report.setResistenciaGlicilciclinas(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 10L));
        report.setResistenciaGlicopeptideos(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 11L));
        report.setResistenciaInibidoresFolato(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 12L));
        report.setResistenciaLincosamidas(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 13L));
        report.setResistenciaMacrolideos(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 14L));
        report.setResistenciaNitrofuranicos(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 15L));
        report.setResistenciaNitroimidazolicos(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 16L));
        report.setResistenciaPolimixinas(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 17L));
        report.setResistenciaQuinolonas(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 18L));
        report.setResistenciaTetraciclinas(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 19L));
        report.setResistenciaAnfenicois(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 23L));
        report.setResistenciaOxazolidinonas(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 24L));
        report.setResistenciaFosfonicos(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 25L));
        report.setResistenciaAzois(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 20L));
        report.setResistenciaEquinocandinas(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 21L));
        report.setResistenciaPolienos(neonatoService.verificarResistenciaClasseAntimicrobiano(antibiogramas, 22L));

        report.setResistenciaAmicacina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 1L));
        report.setResistenciaGentamicina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 2L));
        report.setResistenciaRifampicina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 3L));
        report.setResistenciaAmpicilina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 4L));
        report.setResistenciaAmpicilinaSulbactam(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 5L));
        report.setResistenciaAmoxicilinaAcidoClavulanico(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 6L));
        report.setResistenciaBenzilpenicilina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 7L));
        report.setResistenciaCefoxitina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 8L));
        report.setResistenciaOxacilina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 9L));
        report.setResistenciaPenicilina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 10L));
        report.setResistenciaPiperacilinaTazobactam(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 11L));
        report.setResistenciaTicarcilinaAcidoClavulanico(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 12L));
        report.setResistenciaDoripenem(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 13L));
        report.setResistenciaErtapenem(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 14L));
        report.setResistenciaImipenem(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 15L));
        report.setResistenciaMeropenem(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 16L));
        report.setResistenciaCefalexina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 17L));
        report.setResistenciaCefadroxil(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 18L));
        report.setResistenciaCefalotina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 19L));
        report.setResistenciaCefazolina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 20L));
        report.setResistenciaCefuroxima(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 21L));
        report.setResistenciaCefaclor(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 22L));
        report.setResistenciaCeftriaxona(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 23L));
        report.setResistenciaCeftaxima(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 24L));
        report.setResistenciaCeftazidima(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 25L));
        report.setResistenciaCefepima(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 26L));
        report.setResistenciaCefpiroma(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 27L));
        report.setResistenciaKefazol(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 28L));
        report.setResistenciaTigeciclina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 29L));
        report.setResistenciaVancomicina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 30L));
        report.setResistenciaTeicoplanina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 31L));
        report.setResistenciaTelavancina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 32L));
        report.setResistenciaSulfazotrim(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 33L));
        report.setResistenciaTrimetoprimaSulfametoxazol(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 34L));
        report.setResistenciaClindamicina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 35L));
        report.setResistenciaAzitromicina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 36L));
        report.setResistenciaEritromicina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 37L));
        report.setResistenciaNitrofurantoina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 38L));
        report.setResistenciaMetronidazol(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 39L));
        report.setResistenciaColistina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 40L));
        report.setResistenciaPolimixinaB(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 41L));
        report.setResistenciaCiprofloxacina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 42L));
        report.setResistenciaLevofloxacina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 43L));
        report.setResistenciaMoxifloxacina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 44L));
        report.setResistenciaNorfloxacina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 45L));
        report.setResistenciaTetraciclina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 46L));
        report.setResistenciaFluconazol(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 47L));
        report.setResistenciaVoriconazol(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 48L));
        report.setResistenciaCetoconazol(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 49L));
        report.setResistenciaMiconazol(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 50L));
        report.setResistenciaCapsofungina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 51L));
        report.setResistenciaMicafungina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 52L));
        report.setResistenciaAnfotericinaB(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 53L));
        report.setResistenciaNistatina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 54L));
        report.setResistenciaClorafenicol(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 55L));
        report.setResistenciaNeomicina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 57L));
        report.setResistenciaTobramicina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 58L));
        report.setResistenciaLinezolida(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 59L));
        report.setResistenciaFosfomicina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 60L));
        report.setResistenciaAmoxicilina(neonatoService.verificarResistenciaAntimicrobiano(antibiogramas, 61L));

        return  report;
    }
}
