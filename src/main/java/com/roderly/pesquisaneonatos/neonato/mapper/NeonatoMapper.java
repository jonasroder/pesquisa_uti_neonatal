package com.roderly.pesquisaneonatos.neonato.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.causa_obito.model.CausaObito;
import com.roderly.pesquisaneonatos.cadastros_gerais.idade_gestacional.model.IdadeGestacional;
import com.roderly.pesquisaneonatos.cadastros_gerais.local_nascimento.model.LocalNascimento;
import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.model.MotivoInternacao;
import com.roderly.pesquisaneonatos.cadastros_gerais.peso_nascimento.model.PesoNascimento;
import com.roderly.pesquisaneonatos.cadastros_gerais.rotura_membrana.model.RoturaMembrana;
import com.roderly.pesquisaneonatos.cadastros_gerais.sexo.model.Sexo;
import com.roderly.pesquisaneonatos.cadastros_gerais.sitio_cirurgia.model.SitioCirurgia;
import com.roderly.pesquisaneonatos.cadastros_gerais.sitio_malformacao.model.SitioMalformacao;
import com.roderly.pesquisaneonatos.cadastros_gerais.tipo_parto.model.TipoParto;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.neonato.dto.request.NeonatoRequest;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoListResponse;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoResponse;
import com.roderly.pesquisaneonatos.neonato.excel.NeonatoGrupoControleReportData;
import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import com.roderly.pesquisaneonatos.neonato.service.NeonatoService;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.ClasseAntimicrobianoCountProjection;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.EventoCountProjection;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class NeonatoMapper {

    public static Neonato convertNeonatoRequestToNeonato(NeonatoRequest request) {

        var sexo = request.idSexo() != null ? new Sexo(request.idSexo()) : null;
        var pesoNascimento = request.idPesoNascimento() != null ? new PesoNascimento(request.idPesoNascimento()) : null;
        var motivoInternacao = request.idMotivoInternacao() != null ? new MotivoInternacao(request.idMotivoInternacao()) : null;
        var localNascimento = request.idLocalNascimento() != null ? new LocalNascimento(request.idLocalNascimento()) : null;
        var idadeGestacional = request.idIdadeGestacional() != null ? new IdadeGestacional(request.idIdadeGestacional()) : null;
        var tipoParto = request.idTipoParto() != null ? new TipoParto(request.idTipoParto()) : null;
        var roturaMembrana = request.idRoturaMembrana() != null ? new RoturaMembrana(request.idRoturaMembrana()) : null;
        var sitioMalFormacao = request.idSitioMalformacao() != null ? new SitioMalformacao(request.idSitioMalformacao()) : null;
        var sitioCirurgia = request.idSitioCirurgia() != null ? new SitioCirurgia(request.idSitioCirurgia()) : null;
        var causaObito = request.idCausaObito() != null ? new CausaObito(request.idCausaObito()) : null;


        var neonato = new Neonato();
        neonato.setIdNeonato(request.idNeonato());
        neonato.setNomeMae(request.nomeMae());
        neonato.setProntuario(request.prontuario());
        neonato.setDataNascimento(request.dataNascimento());
        neonato.setDataInternacao(request.dataInternacao());
        neonato.setDataDesfecho(request.dataDesfecho());
        neonato.setObito(request.obito());
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
        neonato.setSitioMalformacao(sitioMalFormacao);
        neonato.setSitioCirurgia(sitioCirurgia);
        neonato.setCausaObito(causaObito);

        return neonato;
    }


    public static NeonatoResponse convertNeonatoToNeonatoResponse(Neonato neonato) {
        return new NeonatoResponse(
                neonato.getIdNeonato(),
                neonato.getNomeMae(),
                neonato.getProntuario(),
                neonato.getDataNascimento(),
                neonato.getDataInternacao(),
                neonato.getDataDesfecho(),
                neonato.getObito(),
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
                neonato.getSitioMalformacao() != null ? neonato.getSitioMalformacao().getIdSitioMalformacao() : null,
                neonato.getSitioCirurgia() != null ? neonato.getSitioCirurgia().getIdSitioCirurgia() : null,
                neonato.getCausaObito() != null ? neonato.getCausaObito().getIdCausaObito() : null,
                neonato.getRiscoInfeccio(),
                neonato.getSepseClinica()
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


    public static NeonatoGrupoControleReportData convertToNeonatoGrupoControleReportData(Neonato neonato, List<EventoCountProjection> eventos, Long diasUsoATB, Long diasUsoATF, List<ClasseAntimicrobianoCountProjection> classeAntimicrobiano, NeonatoService neonatoService) {

        var dataNascimento = neonato.getDataNascimento() != null ? DateUtil.LocalDateToDateBR(neonato.getDataNascimento()) : null;
        var dataInternacao = neonato.getDataInternacao() != null ? DateUtil.LocalDateToDateBR(neonato.getDataInternacao()) : null;
        var dataDesfecho = neonato.getDataDesfecho() != null ? DateUtil.LocalDateToDateBR(neonato.getDataDesfecho()) : null;
        var ano = neonato.getDataInternacao() != null ? neonato.getDataInternacao().getYear() : null;
        var diasInternacao = DateUtil.calcularDiferencaDias(neonato.getDataInternacao(), neonato.getDataDesfecho());

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
        report.setMotivoInternacaoCodigo(neonato.getMotivoInternacao() != null ? neonato.getMotivoInternacao().getCodigo() : null);
        report.setPesoGramas(neonato.getPesoGramas());
        report.setPesoNascimentoCodigo(neonato.getPesoNascimento() != null ? neonato.getPesoNascimento().getCodigo() : null);
        report.setIdadeGestacionalCodigo(neonato.getIdadeGestacional() != null ? neonato.getIdadeGestacional().getCodigo() : null);
        report.setTipoPartoCodigo(neonato.getTipoParto() != null ? neonato.getTipoParto().getCodigo() : null);
        report.setRoturaMembranaCodigo(neonato.getRoturaMembrana() != null ? neonato.getRoturaMembrana().getCodigo() : null);
        report.setMalformacao(neonato.getSitioMalformacao() != null ? 1L : 0L);
        report.setCirurgia(neonato.getSitioCirurgia() != null ? 1L : 0L);

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
}
