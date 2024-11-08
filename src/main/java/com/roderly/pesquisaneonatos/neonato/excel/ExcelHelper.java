package com.roderly.pesquisaneonatos.neonato.excel;

import com.roderly.pesquisaneonatos.common.excel.ExcelColumnMapping;

import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {

    public static List<ExcelColumnMapping> createGrupoControleColumnMapping() {
        List<ExcelColumnMapping> columnMappings = new ArrayList<>();
        columnMappings.add(new ExcelColumnMapping("ANO", "getAno"));
        columnMappings.add(new ExcelColumnMapping("PRONTUÁRIO", "getProntuario"));
        columnMappings.add(new ExcelColumnMapping("DATA DE NASCIMENTO", "getDataNascimento"));
        columnMappings.add(new ExcelColumnMapping("DATA DE INTERNAÇÃO", "getDataInternacao"));
        columnMappings.add(new ExcelColumnMapping("DATA DO DESFECHO", "getDataDesfecho"));
        columnMappings.add(new ExcelColumnMapping("DIAS DE INTERNAÇÃO", "getDiasInternacao"));
        columnMappings.add(new ExcelColumnMapping("DESFECHO", "getObito"));
        columnMappings.add(new ExcelColumnMapping("PROCEDÊNCIA", "getLocalNascimentoCodigo"));
        columnMappings.add(new ExcelColumnMapping("SEXO AO NASCIMENTO", "getSexoCodigo"));
        columnMappings.add(new ExcelColumnMapping("INDICAÇÃO DE INTERNAÇÃO", "getMotivoInternacaoCodigo"));
        columnMappings.add(new ExcelColumnMapping("PESO AO NASCER", "getPesoGramas"));
        columnMappings.add(new ExcelColumnMapping("CLASSIFICAÇÃO PESO", "getPesoNascimentoCodigo"));
        columnMappings.add(new ExcelColumnMapping("CLASSIFICAÇÃO IDADE GESTACIONAL", "getIdadeGestacionalCodigo"));
        columnMappings.add(new ExcelColumnMapping("TIPO DE PARTO", "getTipoPartoCodigo"));
        columnMappings.add(new ExcelColumnMapping("BOLSA ROTA", "getRoturaMembranaCodigo"));
        columnMappings.add(new ExcelColumnMapping("MALFORMAÇÃO", "getMalformacao"));
        columnMappings.add(new ExcelColumnMapping("CIRURGIA", "getCirurgia"));

        columnMappings.add(new ExcelColumnMapping("FLEBOTOMIA", "getFlebotomia"));
        columnMappings.add(new ExcelColumnMapping("DIAS FLEBOTOMIA", "getDiasFlebotomia"));

        columnMappings.add(new ExcelColumnMapping("CVU", "getCVU"));
        columnMappings.add(new ExcelColumnMapping("DIAS CVU", "getDiasCVU"));

        columnMappings.add(new ExcelColumnMapping("PICC", "getPICC"));
        columnMappings.add(new ExcelColumnMapping("DIAS PICC", "getDiasPICC"));

        columnMappings.add(new ExcelColumnMapping("INTUBAÇÃO", "getIntubacao"));
        columnMappings.add(new ExcelColumnMapping("DIAS INTUBAÇÃO", "getDiasIntubacao"));

        columnMappings.add(new ExcelColumnMapping("SONDA", "getSonda"));
        columnMappings.add(new ExcelColumnMapping("DIAS SONDA", "getDiasSonda"));

        columnMappings.add(new ExcelColumnMapping("NUTRIÇÃO PARENTERAL", "getNutricaoParenteral"));
        columnMappings.add(new ExcelColumnMapping("DIAS NUTRIÇÃO PARENTERAL", "getDiasNutricaoParenteral"));

        columnMappings.add(new ExcelColumnMapping("DRENO", "getDreno"));
        columnMappings.add(new ExcelColumnMapping("DIAS DRENO", "getDiasDreno"));

        columnMappings.add(new ExcelColumnMapping("USO ATB", "getUsoATB"));
        columnMappings.add(new ExcelColumnMapping("ATB DIAS", "getDiasUsoATB"));

        columnMappings.add(new ExcelColumnMapping("AMINOGLICOSÍDEOS", "getAminoglicosideos"));
        columnMappings.add(new ExcelColumnMapping("AMINOGLICOSÍDEOS DIAS", "getDiasAminoglicosideos"));

        columnMappings.add(new ExcelColumnMapping("BETALACTÂMICOS", "getBetalactamicos"));
        columnMappings.add(new ExcelColumnMapping("BETALACTÂMICOS DIAS", "getDiasBetalactamicos"));

        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 1 GER", "getCefalosporinasPrimeiraGeracao"));
        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 1 GER DIAS", "getDiasCefalosporinasPrimeiraGeracao"));

        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 2 GER", "getCefalosporinasSegundaGeracao"));
        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 2 GER DIAS", "getDiasCefalosporinasSegundaGeracao"));

        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 3 GER", "getCefalosporinasTerceiraGeracao"));
        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 3 GER DIAS", "getDiasCefalosporinasTerceiraGeracao"));

        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 4 GER", "getCefalosporinasQuartaGeracao"));
        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 4 GER DIAS", "getDiasCefalosporinasQuartaGeracao"));

        columnMappings.add(new ExcelColumnMapping("GLICOPEPTÍDEOS", "getGlicopeptideos"));
        columnMappings.add(new ExcelColumnMapping("GLICOPEPTÍDEOS DIAS", "getDiasGlicopeptideos"));

        columnMappings.add(new ExcelColumnMapping("NITROIMIDAZÓLICOS", "getNitroimidazolicos"));
        columnMappings.add(new ExcelColumnMapping("NITROIMIDAZÓLICOS DIAS", "getDiasNitroimidazolicos"));

        columnMappings.add(new ExcelColumnMapping("CARBAPENÊMICOS", "getCarbapenemicos"));
        columnMappings.add(new ExcelColumnMapping("CARBAPENÊMICOS DIAS", "getDiasCarbapenemicos"));

        columnMappings.add(new ExcelColumnMapping("QUINOLONAS", "getQuinolonas"));
        columnMappings.add(new ExcelColumnMapping("QUINOLONAS DIAS", "getDiasQuinolonas"));

        columnMappings.add(new ExcelColumnMapping("ANSAMICINAS", "getAnsamicinas"));
        columnMappings.add(new ExcelColumnMapping("ANSAMICINAS DIAS", "getDiasAnsamicinas"));

        columnMappings.add(new ExcelColumnMapping("GLICILCICLINAS", "getGlicilciclinas"));
        columnMappings.add(new ExcelColumnMapping("GLICILCICLINAS DIAS", "getDiasGlicilciclinas"));

        columnMappings.add(new ExcelColumnMapping("INIBIDORES DE FOLATO", "getInibidoresDeFolato"));
        columnMappings.add(new ExcelColumnMapping("INIBIDORES DE FOLATO DIAS", "getDiasInibidoresDeFolato"));

        columnMappings.add(new ExcelColumnMapping("LINCOSAMIDAS", "getLincosamidas"));
        columnMappings.add(new ExcelColumnMapping("LINCOSAMIDAS DIAS", "getDiasLincosamidas"));

        columnMappings.add(new ExcelColumnMapping("MACROLIDEOS", "getMacrolideos"));
        columnMappings.add(new ExcelColumnMapping("MACROLIDEOS DIAS", "getDiasMacrolideos"));

        columnMappings.add(new ExcelColumnMapping("NITROFURANICOS", "getNitrofuranicos"));
        columnMappings.add(new ExcelColumnMapping("NITROFURANICOS DIAS", "getDiasNitrofuranicos"));

        columnMappings.add(new ExcelColumnMapping("POLIMIXINAS", "getPolimixinas"));
        columnMappings.add(new ExcelColumnMapping("POLIMIXINAS DIAS", "getDiasPolimixinas"));

        columnMappings.add(new ExcelColumnMapping("TETRACICLINAS", "getTetraciclinas"));
        columnMappings.add(new ExcelColumnMapping("TETRACICLINAS DIAS", "getDiasTetraciclinas"));

        columnMappings.add(new ExcelColumnMapping("USO ATF", "getUsoATF"));
        columnMappings.add(new ExcelColumnMapping("ATF DIAS", "getDiasUsoATF"));

        columnMappings.add(new ExcelColumnMapping("AZOIS", "getAzois"));
        columnMappings.add(new ExcelColumnMapping("AZOIS DIAS", "getDiasAzois"));

        columnMappings.add(new ExcelColumnMapping("EQUINOCANDINAS", "getEquinocandinas"));
        columnMappings.add(new ExcelColumnMapping("EQUINOCANDINAS DIAS", "getDiasEquinocandinas"));

        columnMappings.add(new ExcelColumnMapping("POLIENOS", "getPolienos"));
        columnMappings.add(new ExcelColumnMapping("POLIENOS DIAS", "getDiasPolienos"));

        columnMappings.add(new ExcelColumnMapping("MOTIVO ÓBITO", "getMotivoObito"));

        return columnMappings;
    }


    public static List<ExcelColumnMapping> createGrupoInfectadoColumnMapping() {
        List<ExcelColumnMapping> columnMappings = new ArrayList<>();
        columnMappings.add(new ExcelColumnMapping("ANO", "getAno"));
        columnMappings.add(new ExcelColumnMapping("PRONTUÁRIO", "getProntuario"));
        columnMappings.add(new ExcelColumnMapping("PACIENTE", "getPaciente"));
        columnMappings.add(new ExcelColumnMapping("DATA DE NASCIMENTO", "getDataNascimento"));
        columnMappings.add(new ExcelColumnMapping("DATA DE INTERNAÇÃO", "getDataInternacao"));
        columnMappings.add(new ExcelColumnMapping("DATA DO DESFECHO", "getDataDesfecho"));
        columnMappings.add(new ExcelColumnMapping("DIAS DE INTERNAÇÃO", "getDiasInternacao"));
        columnMappings.add(new ExcelColumnMapping("DESFECHO", "getObito"));
        columnMappings.add(new ExcelColumnMapping("PROCEDÊNCIA", "getLocalNascimentoCodigo"));
        columnMappings.add(new ExcelColumnMapping("INDICAÇÃO DE INTERNAÇÃO", "getMotivoInternacaoCodigo"));
        columnMappings.add(new ExcelColumnMapping("SEXO AO NASCIMENTO", "getSexoCodigo"));
        columnMappings.add(new ExcelColumnMapping("PESO AO NASCER", "getPesoGramas"));
        columnMappings.add(new ExcelColumnMapping("CLASSIFICAÇÃO PESO", "getPesoNascimentoCodigo"));
        columnMappings.add(new ExcelColumnMapping("CLASSIFICAÇÃO IDADE GESTACIONAL", "getIdadeGestacionalCodigo"));
        columnMappings.add(new ExcelColumnMapping("TIPO DE PARTO", "getTipoPartoCodigo"));
        columnMappings.add(new ExcelColumnMapping("BOLSA ROTA", "getRoturaMembranaCodigo"));

        columnMappings.add(new ExcelColumnMapping("APGAR 1", "getApgar1"));
        columnMappings.add(new ExcelColumnMapping("APGAR 5", "getApgar5"));
        columnMappings.add(new ExcelColumnMapping("MALFORMAÇÃO", "getMalformacao"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA MALFORMAÇÃO 1", "getSitioMalformacao1"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA MALFORMAÇÃO 2", "getSitioMalformacao2"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA MALFORMAÇÃO 3", "getSitioMalformacao3"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA MALFORMAÇÃO 4", "getSitioMalformacao4"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA MALFORMAÇÃO 5", "getSitioMalformacao5"));

        columnMappings.add(new ExcelColumnMapping("CIRURGIA PRÉVIA", "getCirurgiaPrevia"));
        columnMappings.add(new ExcelColumnMapping("CIRURGIA", "getCirurgia"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA CIRURGIA 1", "getSitioCirurgia1"));
        columnMappings.add(new ExcelColumnMapping("DATA DA CIRURGIA 1", "getDataCirurgia1"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA CIRURGIA 2", "getSitioCirurgia2"));
        columnMappings.add(new ExcelColumnMapping("DATA DA CIRURGIA 2", "getDataCirurgia2"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA CIRURGIA 3", "getSitioCirurgia3"));
        columnMappings.add(new ExcelColumnMapping("DATA DA CIRURGIA 3", "getDataCirurgia3"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA CIRURGIA 4", "getSitioCirurgia4"));
        columnMappings.add(new ExcelColumnMapping("DATA DA CIRURGIA 4", "getDataCirurgia4"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA CIRURGIA 5", "getSitioCirurgia5"));
        columnMappings.add(new ExcelColumnMapping("DATA DA CIRURGIA 5", "getDataCirurgia5"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA CIRURGIA 6", "getSitioCirurgia6"));
        columnMappings.add(new ExcelColumnMapping("DATA DA CIRURGIA 6", "getDataCirurgia6"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA CIRURGIA 7", "getSitioCirurgia7"));
        columnMappings.add(new ExcelColumnMapping("DATA DA CIRURGIA 7", "getDataCirurgia7"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA CIRURGIA 8", "getSitioCirurgia8"));
        columnMappings.add(new ExcelColumnMapping("DATA DA CIRURGIA 8", "getDataCirurgia8"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA CIRURGIA 9", "getSitioCirurgia9"));
        columnMappings.add(new ExcelColumnMapping("DATA DA CIRURGIA 9", "getDataCirurgia9"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DA CIRURGIA 10", "getSitioCirurgia10"));
        columnMappings.add(new ExcelColumnMapping("DATA DA CIRURGIA 10", "getDataCirurgia10"));
        columnMappings.add(new ExcelColumnMapping("USO FLEBOTOMIA", "getUsoFlebotomia"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS FLEBOTOMIA", "getDiasTotaisFlebotomia"));
        columnMappings.add(new ExcelColumnMapping("DIAS FLEBOTOMIA ATÉ A 1° INFECÇÃO", "getDiasFlebotomia1Infencao"));

        columnMappings.add(new ExcelColumnMapping("USO CVU (UMBILICAL)", "getUsoCvu"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS CVU (UMBILICAL)", "getDiasTotaisCvu"));
        columnMappings.add(new ExcelColumnMapping("DIAS CVU (UMBILICAL) ATÉ A 1° INFECÇÃO", "getDiasCvu1Infencao"));

        columnMappings.add(new ExcelColumnMapping("USO PICC", "getUsoPicc"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS PICC", "getDiasTotaisPicc"));
        columnMappings.add(new ExcelColumnMapping("DIAS PICC ATÉ A 1° INFECÇÃO", "getDiasPicc1Infencao"));

        columnMappings.add(new ExcelColumnMapping("USO ENTUBAÇÃO", "getUsoEntubacao"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS ENTUBAÇÃO", "getDiasTotaisEntubacao"));
        columnMappings.add(new ExcelColumnMapping("DIAS ENTUBAÇÃO ATÉ A 1° INFECÇÃO", "getDiasEntubacao1Infencao"));

        columnMappings.add(new ExcelColumnMapping("USO SONDA VESICAL", "getUsoSondaVesical"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS SONDA VESICAL", "getDiasTotaisSondaVesical"));
        columnMappings.add(new ExcelColumnMapping("DIAS SONDA VESICAL ATÉ A 1° INFECÇÃO", "getDiasSondaVesical1Infencao"));

        columnMappings.add(new ExcelColumnMapping("USO NUTRIÇÃO PARENTERAL", "getUsoNutricaoParenteral"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS NUTRIÇÃO PARENTERAL", "getDiasTotaisNutricaoParenteral"));
        columnMappings.add(new ExcelColumnMapping("DIAS NUTRIÇÃO PARENTERAL ATÉ A 1° INFECÇÃO", "getDiasNutricaoParenteral1Infencao"));

        columnMappings.add(new ExcelColumnMapping("USO DRENO", "getUsoDreno"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS DRENO", "getDiasTotaisDreno"));
        columnMappings.add(new ExcelColumnMapping("DIAS DRENO ATÉ A 1° INFECÇÃO", "getDiasDreno1Infencao"));

        return columnMappings;
    }
}
