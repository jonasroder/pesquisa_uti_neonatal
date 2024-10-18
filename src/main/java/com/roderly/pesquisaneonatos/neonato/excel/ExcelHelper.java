package com.roderly.pesquisaneonatos.neonato.excel;

import com.roderly.pesquisaneonatos.common.excel.ExcelColumnMapping;

import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {

    public static List<ExcelColumnMapping> createColumnMappings() {
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
}
