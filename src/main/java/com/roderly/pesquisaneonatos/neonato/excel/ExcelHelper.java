package com.roderly.pesquisaneonatos.neonato.excel;

import com.roderly.pesquisaneonatos.common.excel.ExcelColumnMapping;
import com.roderly.pesquisaneonatos.neonato.mapper.NeonatoMapper;

import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {

    public static List<ExcelColumnMapping> createGrupoControleColumnMapping() {
        List<ExcelColumnMapping> columnMappings = new ArrayList<>();
        columnMappings.add(new ExcelColumnMapping("ANO", "ano"));
        columnMappings.add(new ExcelColumnMapping("PRONTUÁRIO", "prontuario"));
        columnMappings.add(new ExcelColumnMapping("DATA DE NASCIMENTO", "dataNascimento"));
        columnMappings.add(new ExcelColumnMapping("DATA DE INTERNAÇÃO", "dataInternacao"));
        columnMappings.add(new ExcelColumnMapping("DATA DO DESFECHO", "dataDesfecho"));
        columnMappings.add(new ExcelColumnMapping("DIAS DE INTERNAÇÃO", "diasInternacao"));
        columnMappings.add(new ExcelColumnMapping("DESFECHO", "obito"));
        columnMappings.add(new ExcelColumnMapping("PROCEDÊNCIA", "localNascimentoCodigo"));
        columnMappings.add(new ExcelColumnMapping("SEXO AO NASCIMENTO", "sexoCodigo"));
        columnMappings.add(new ExcelColumnMapping("INDICAÇÃO DE INTERNAÇÃO", "motivoInternacaoCodigo"));
        columnMappings.add(new ExcelColumnMapping("PESO AO NASCER", "pesoGramas"));
        columnMappings.add(new ExcelColumnMapping("CLASSIFICAÇÃO PESO", "pesoNascimentoCodigo"));
        columnMappings.add(new ExcelColumnMapping("CLASSIFICAÇÃO IDADE GESTACIONAL", "idadeGestacionalCodigo"));
        columnMappings.add(new ExcelColumnMapping("TIPO DE PARTO", "tipoPartoCodigo"));
        columnMappings.add(new ExcelColumnMapping("BOLSA ROTA", "roturaMembranaCodigo"));
        columnMappings.add(new ExcelColumnMapping("MALFORMAÇÃO", "malformacao"));
        columnMappings.add(new ExcelColumnMapping("CIRURGIA", "cirurgia"));

        columnMappings.add(new ExcelColumnMapping("FLEBOTOMIA", "flebotomia"));
        columnMappings.add(new ExcelColumnMapping("DIAS FLEBOTOMIA", "diasFlebotomia"));

        columnMappings.add(new ExcelColumnMapping("CVU", "CVU"));
        columnMappings.add(new ExcelColumnMapping("DIAS CVU", "diasCVU"));

        columnMappings.add(new ExcelColumnMapping("PICC", "PICC"));
        columnMappings.add(new ExcelColumnMapping("DIAS PICC", "diasPICC"));

        columnMappings.add(new ExcelColumnMapping("INTUBAÇÃO", "intubacao"));
        columnMappings.add(new ExcelColumnMapping("DIAS INTUBAÇÃO", "diasIntubacao"));

        columnMappings.add(new ExcelColumnMapping("SONDA", "sonda"));
        columnMappings.add(new ExcelColumnMapping("DIAS SONDA", "diasSonda"));

        columnMappings.add(new ExcelColumnMapping("NUTRIÇÃO PARENTERAL", "nutricaoParenteral"));
        columnMappings.add(new ExcelColumnMapping("DIAS NUTRIÇÃO PARENTERAL", "diasNutricaoParenteral"));

        columnMappings.add(new ExcelColumnMapping("DRENO", "dreno"));
        columnMappings.add(new ExcelColumnMapping("DIAS DRENO", "diasDreno"));

        columnMappings.add(new ExcelColumnMapping("USO ATB", "usoATB"));
        columnMappings.add(new ExcelColumnMapping("ATB DIAS", "diasUsoATB"));

        columnMappings.add(new ExcelColumnMapping("AMINOGLICOSÍDEOS", "aminoglicosideos"));
        columnMappings.add(new ExcelColumnMapping("AMINOGLICOSÍDEOS DIAS", "diasAminoglicosideos"));

        columnMappings.add(new ExcelColumnMapping("BETALACTÂMICOS", "betalactamicos"));
        columnMappings.add(new ExcelColumnMapping("BETALACTÂMICOS DIAS", "diasBetalactamicos"));

        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 1 GER", "cefalosporinasPrimeiraGeracao"));
        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 1 GER DIAS", "diasCefalosporinasPrimeiraGeracao"));

        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 2 GER", "cefalosporinasSegundaGeracao"));
        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 2 GER DIAS", "diasCefalosporinasSegundaGeracao"));

        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 3 GER", "cefalosporinasTerceiraGeracao"));
        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 3 GER DIAS", "diasCefalosporinasTerceiraGeracao"));

        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 4 GER", "cefalosporinasQuartaGeracao"));
        columnMappings.add(new ExcelColumnMapping("CEFALOSPORINAS 4 GER DIAS", "diasCefalosporinasQuartaGeracao"));

        columnMappings.add(new ExcelColumnMapping("GLICOPEPTÍDEOS", "glicopeptideos"));
        columnMappings.add(new ExcelColumnMapping("GLICOPEPTÍDEOS DIAS", "diasGlicopeptideos"));

        columnMappings.add(new ExcelColumnMapping("NITROIMIDAZÓLICOS", "nitroimidazolicos"));
        columnMappings.add(new ExcelColumnMapping("NITROIMIDAZÓLICOS DIAS", "diasNitroimidazolicos"));

        columnMappings.add(new ExcelColumnMapping("CARBAPENÊMICOS", "carbapenemicos"));
        columnMappings.add(new ExcelColumnMapping("CARBAPENÊMICOS DIAS", "diasCarbapenemicos"));

        columnMappings.add(new ExcelColumnMapping("QUINOLONAS", "quinolonas"));
        columnMappings.add(new ExcelColumnMapping("QUINOLONAS DIAS", "diasQuinolonas"));

        columnMappings.add(new ExcelColumnMapping("ANSAMICINAS", "ansamicinas"));
        columnMappings.add(new ExcelColumnMapping("ANSAMICINAS DIAS", "diasAnsamicinas"));

        columnMappings.add(new ExcelColumnMapping("GLICILCICLINAS", "glicilciclinas"));
        columnMappings.add(new ExcelColumnMapping("GLICILCICLINAS DIAS", "diasGlicilciclinas"));

        columnMappings.add(new ExcelColumnMapping("INIBIDORES DE FOLATO", "inibidoresDeFolato"));
        columnMappings.add(new ExcelColumnMapping("INIBIDORES DE FOLATO DIAS", "diasInibidoresDeFolato"));

        columnMappings.add(new ExcelColumnMapping("LINCOSAMIDAS", "lincosamidas"));
        columnMappings.add(new ExcelColumnMapping("LINCOSAMIDAS DIAS", "diasLincosamidas"));

        columnMappings.add(new ExcelColumnMapping("MACROLIDEOS", "macrolideos"));
        columnMappings.add(new ExcelColumnMapping("MACROLIDEOS DIAS", "diasMacrolideos"));

        columnMappings.add(new ExcelColumnMapping("NITROFURANICOS", "nitrofuranicos"));
        columnMappings.add(new ExcelColumnMapping("NITROFURANICOS DIAS", "diasNitrofuranicos"));

        columnMappings.add(new ExcelColumnMapping("POLIMIXINAS", "polimixinas"));
        columnMappings.add(new ExcelColumnMapping("POLIMIXINAS DIAS", "diasPolimixinas"));

        columnMappings.add(new ExcelColumnMapping("TETRACICLINAS", "tetraciclinas"));
        columnMappings.add(new ExcelColumnMapping("TETRACICLINAS DIAS", "diasTetraciclinas"));

        columnMappings.add(new ExcelColumnMapping("USO ATF", "usoATF"));
        columnMappings.add(new ExcelColumnMapping("ATF DIAS", "diasUsoATF"));

        columnMappings.add(new ExcelColumnMapping("AZOIS", "azois"));
        columnMappings.add(new ExcelColumnMapping("AZOIS DIAS", "diasAzois"));

        columnMappings.add(new ExcelColumnMapping("EQUINOCANDINAS", "equinocandinas"));
        columnMappings.add(new ExcelColumnMapping("EQUINOCANDINAS DIAS", "diasEquinocandinas"));

        columnMappings.add(new ExcelColumnMapping("POLIENOS", "polienos"));
        columnMappings.add(new ExcelColumnMapping("POLIENOS DIAS", "diasPolienos"));

        columnMappings.add(new ExcelColumnMapping("MOTIVO ÓBITO", "motivoObito"));

        return columnMappings;
    }


    public static List<ExcelColumnMapping> createGrupoInfectadoColumnMapping() {

        List<ExcelColumnMapping> columnMappings = new ArrayList<>();
        columnMappings.add(new ExcelColumnMapping("ANO", "ano"));
        columnMappings.add(new ExcelColumnMapping("PRONTUÁRIO", "prontuario"));
        columnMappings.add(new ExcelColumnMapping("PACIENTE", "paciente"));
        columnMappings.add(new ExcelColumnMapping("DATA DE NASCIMENTO", "dataNascimento"));
        columnMappings.add(new ExcelColumnMapping("DATA DE INTERNAÇÃO", "dataInternacao"));
        columnMappings.add(new ExcelColumnMapping("DATA DO DESFECHO", "dataDesfecho"));
        columnMappings.add(new ExcelColumnMapping("DIAS DE INTERNAÇÃO", "diasInternacao"));
        columnMappings.add(new ExcelColumnMapping("DESFECHO", "obito"));
        columnMappings.add(new ExcelColumnMapping("PROCEDÊNCIA", "localNascimentoCodigo"));
        columnMappings.add(new ExcelColumnMapping("INDICAÇÃO DE INTERNAÇÃO", "motivoInternacaoCodigo"));
        columnMappings.add(new ExcelColumnMapping("SEXO AO NASCIMENTO", "sexoCodigo"));
        columnMappings.add(new ExcelColumnMapping("PESO AO NASCER", "pesoGramas"));
        columnMappings.add(new ExcelColumnMapping("CLASSIFICAÇÃO PESO", "pesoNascimentoCodigo"));
        columnMappings.add(new ExcelColumnMapping("CLASSIFICAÇÃO IDADE GESTACIONAL", "idadeGestacionalCodigo"));
        columnMappings.add(new ExcelColumnMapping("TIPO DE PARTO", "tipoPartoCodigo"));
        columnMappings.add(new ExcelColumnMapping("BOLSA ROTA", "roturaMembranaCodigo"));
        columnMappings.add(new ExcelColumnMapping("APGAR 1", "apgar1"));
        columnMappings.add(new ExcelColumnMapping("APGAR 5", "apgar5"));

        // Sitios Malformacao
        columnMappings.add(new ExcelColumnMapping("MALFORMAÇÃO", "malformacao"));
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + "º SÍTIO DA MALFORMAÇÃO", "SitioMalformacoes_" + i + "_codigo"));
        }

        //Cirurgias
        columnMappings.add(new ExcelColumnMapping("CIRURGIA", "Cirurgia_uso"));
        columnMappings.add(new ExcelColumnMapping("Nº CIRURGIAS", "Cirurgia_diasTotaisUso"));
        for (int i = 1; i <= NeonatoMapper.numeroAnalisesInfeccoes; i++) {
            columnMappings.add(new ExcelColumnMapping("CIRURGIAS ATÉ A " + i + "ª INFECÇÃO", "Cirurgia_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("CIRURGIAS APÓS A " + i + "ª INFECÇÃO", "Cirurgia_diasAposInfecao" + i));
        }
        for (int i = 0; i < 10; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DATA DA CIRURGIA", "ColunasCirurgia_" + i + "_dataCirurgia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP SÍTIO DA CIRURGIA", "ColunasCirurgia_" + i + "_codigoSitioCirurgia"));
        }

        //Flebotomia
        columnMappings.add(new ExcelColumnMapping("USO FLEBOTOMIA", "Flebotomia_uso"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS FLEBOTOMIA", "Flebotomia_diasTotaisUso"));
        for (int i = 1; i <= NeonatoMapper.numeroAnalisesInfeccoes; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS FLEBOTOMIAS ATÉ A " + i + "ª INFECÇÃO", "Flebotomia_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS FLEBOTOMIAS APÓS A " + i + "ª INFECÇÃO", "Flebotomia_diasAposInfecao" + i));
        }

        //CVU
        columnMappings.add(new ExcelColumnMapping("USO CVU", "Cvu_uso"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS CVU", "Cvu_diasTotaisUso"));
        for (int i = 1; i <= NeonatoMapper.numeroAnalisesInfeccoes; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS CVU ATÉ A " + i + "ª INFECÇÃO", "Cvu_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS CVU APÓS A " + i + "ª INFECÇÃO", "Cvu_diasAposInfecao" + i));
        }

        //Picc
        columnMappings.add(new ExcelColumnMapping("USO PICC", "Picc_uso"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS PICC", "Picc_diasTotaisUso"));
        for (int i = 1; i <= NeonatoMapper.numeroAnalisesInfeccoes; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS PICC ATÉ A " + i + "ª INFECÇÃO", "Picc_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS PICC APÓS A " + i + "ª INFECÇÃO", "Picc_diasAposInfecao" + i));
        }

        //Entubacao
        columnMappings.add(new ExcelColumnMapping("USO ENTUBAÇÃO", "Entubacao_uso"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS ENTUBAÇÃO", "Entubacao_diasTotaisUso"));
        for (int i = 1; i <= NeonatoMapper.numeroAnalisesInfeccoes; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS ENTUBAÇÃO ATÉ A " + i + "ª INFECÇÃO", "Entubacao_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS ENTUBAÇÃO APÓS A " + i + "ª INFECÇÃO", "Entubacao_diasAposInfecao" + i));
        }

        //Sonda Vesical
        columnMappings.add(new ExcelColumnMapping("USO SONDA VESICAL", "SondaVesical_uso"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS SONDA VESICAL", "SondaVesical_diasTotaisUso"));
        for (int i = 1; i <= NeonatoMapper.numeroAnalisesInfeccoes; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS SONDA VESICAL ATÉ A " + i + "ª INFECÇÃO", "SondaVesical_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS SONDA VESICAL APÓS A " + i + "ª INFECÇÃO", "SondaVesical_diasAposInfecao" + i));
        }

        //Nutrição parenteral
        columnMappings.add(new ExcelColumnMapping("USO NUTRIÇÃO PARENTERAL", "NutricaoParenteral_uso"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS NUTRIÇÃO PARENTERAL", "NutricaoParenteral_diasTotaisUso"));
        for (int i = 1; i <= NeonatoMapper.numeroAnalisesInfeccoes; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS NUTRIÇÃO PARENTERAL ATÉ A " + i + "ª INFECÇÃO", "NutricaoParenteral_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS NUTRIÇÃO PARENTERAL APÓS A " + i + "ª INFECÇÃO", "NutricaoParenteral_diasAposInfecao" + i));
        }

        //Nutrição parenteral
        columnMappings.add(new ExcelColumnMapping("USO DRENO", "Dreno_uso"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS DRENO", "Dreno_diasTotaisUso"));
        for (int i = 1; i <= NeonatoMapper.numeroAnalisesInfeccoes; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS DRENO ATÉ A " + i + "ª INFECÇÃO", "Dreno_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS DRENO APÓS A " + i + "ª INFECÇÃO", "Dreno_diasAposInfecao" + i));
        }

        //columnMappings.add(new ExcelColumnMapping("SEPSE CLÍNICA", "sepseClinica"));
        //columnMappings.add(new ExcelColumnMapping("RISCO INFECCIOSO", "riscoInfeccioso"));

        // Coleta Sangue
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - DATA DA COLETA", "ColetaSangue_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - NÚMERO DE ISOLADOS", "ColetaSangue_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - ESPÉCIE ISOLADA", "ColetaSangue_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - CLASSIFICAÇÃO ISOLADO", "ColetaSangue_" + i + "_tipoIsolado"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - PERFIL RESISTÊNCIA", "ColetaSangue_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - MECANISMO RESISTÊNCIA", "ColetaSangue_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - INFECÇÃO MISTA", "ColetaSangue_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - 2ª ESPÉCIE ISOLADA", "ColetaSangue_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - CLASSIFICAÇÃO ISOLADO 2", "ColetaSangue_" + i + "_tipoIsolado2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaSangue_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaSangue_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta Secrecao Ocular
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - DATA DA COLETA", "ColetaSecOcular_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - NÚMERO DE ISOLADOS", "ColetaSecOcular_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - ESPÉCIE ISOLADA", "ColetaSecOcular_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - CLASSIFICAÇÃO ISOLADO", "ColetaSecOcular_" + i + "_tipoIsolado"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - PERFIL RESISTÊNCIA", "ColetaSecOcular_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - MECANISMO RESISTÊNCIA", "ColetaSecOcular_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - INFECÇÃO MISTA", "ColetaSecOcular_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - 2ª ESPÉCIE ISOLADA", "ColetaSecOcular_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - CLASSIFICAÇÃO ISOLADO 2", "ColetaSecOcular_" + i + "_tipoIsolado2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaSecOcular_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaSecOcular_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta Liquido Pleural
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - DATA DA COLETA", "ColetaLiqPleural_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - NÚMERO DE ISOLADOS", "ColetaLiqPleural_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - ESPÉCIE ISOLADA", "ColetaLiqPleural_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - CLASSIFICAÇÃO ISOLADO", "ColetaLiqPleural_" + i + "_tipoIsolado"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - PERFIL RESISTÊNCIA", "ColetaLiqPleural_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - MECANISMO RESISTÊNCIA", "ColetaLiqPleural_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - INFECÇÃO MISTA", "ColetaLiqPleural_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - 2ª ESPÉCIE ISOLADA", "ColetaLiqPleural_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - CLASSIFICAÇÃO ISOLADO 2", "ColetaLiqPleural_" + i + "_tipoIsolado2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaLiqPleural_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaLiqPleural_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta LCR
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - DATA DA COLETA", "ColetaLCR_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - NÚMERO DE ISOLADOS", "ColetaLCR_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - ESPÉCIE ISOLADA", "ColetaLCR_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - CLASSIFICAÇÃO ISOLADO", "ColetaLCR_" + i + "_tipoIsolado"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - PERFIL RESISTÊNCIA", "ColetaLCR_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - MECANISMO RESISTÊNCIA", "ColetaLCR_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - INFECÇÃO MISTA", "ColetaLCR_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - 2ª ESPÉCIE ISOLADA", "ColetaLCR_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - CLASSIFICAÇÃO ISOLADO 2", "ColetaLCR_" + i + "_tipoIsolado2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaLCR_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaLCR_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta LÍQ ASCÍTICO
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - DATA DA COLETA", "ColetaLiqAscitico_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - NÚMERO DE ISOLADOS", "ColetaLiqAscitico_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - ESPÉCIE ISOLADA", "ColetaLiqAscitico_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - CLASSIFICAÇÃO ISOLADO", "ColetaLiqAscitico_" + i + "_tipoIsolado"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - PERFIL RESISTÊNCIA", "ColetaLiqAscitico_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - MECANISMO RESISTÊNCIA", "ColetaLiqAscitico_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - INFECÇÃO MISTA", "ColetaLiqAscitico_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - 2ª ESPÉCIE ISOLADA", "ColetaLiqAscitico_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - CLASSIFICAÇÃO ISOLADO 2", "ColetaLiqAscitico_" + i + "_tipoIsolado2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaLiqAscitico_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaLiqAscitico_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta Urina
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - DATA DA COLETA", "ColetaUrina_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - NÚMERO DE ISOLADOS", "ColetaUrina_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - ESPÉCIE ISOLADA", "ColetaUrina_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - CLASSIFICAÇÃO ISOLADO", "ColetaUrina_" + i + "_tipoIsolado"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - PERFIL RESISTÊNCIA", "ColetaUrina_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - MECANISMO RESISTÊNCIA", "ColetaUrina_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - INFECÇÃO MISTA", "ColetaUrina_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - 2ª ESPÉCIE ISOLADA", "ColetaUrina_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - CLASSIFICAÇÃO ISOLADO 2", "ColetaUrina_" + i + "_tipoIsolado2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaUrina_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaUrina_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta Secrecao Pulmonar
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - DATA DA COLETA", "ColetaSecrecaoPulmonar_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - NÚMERO DE ISOLADOS", "ColetaSecrecaoPulmonar_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - ESPÉCIE ISOLADA", "ColetaSecrecaoPulmonar_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP SECREÇÃO PULMONAR - CLASSIFICAÇÃO ISOLADO", "ColetaSecrecaoPulmonar_" + i + "_tipoIsolado"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - PERFIL RESISTÊNCIA", "ColetaSecrecaoPulmonar_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - MECANISMO RESISTÊNCIA", "ColetaSecrecaoPulmonar_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - INFECÇÃO MISTA", "ColetaSecrecaoPulmonar_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - 2ª ESPÉCIE ISOLADA", "ColetaSecrecaoPulmonar_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP SECREÇÃO PULMONAR - CLASSIFICAÇÃO ISOLADO 2", "ColetaSecrecaoPulmonar_" + i + "_tipoIsolado2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaSecrecaoPulmonar_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaSecrecaoPulmonar_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta Ponta Cateter
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - DATA DA COLETA", "ColetaPontaCateter_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - NÚMERO DE ISOLADOS", "ColetaPontaCateter_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - ESPÉCIE ISOLADA", "ColetaPontaCateter_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - CLASSIFICAÇÃO ISOLADO", "ColetaPontaCateter_" + i + "_tipoIsolado"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - PERFIL RESISTÊNCIA", "ColetaPontaCateter_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - MECANISMO RESISTÊNCIA", "ColetaPontaCateter_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - INFECÇÃO MISTA", "ColetaPontaCateter_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - 2ª ESPÉCIE ISOLADA", "ColetaPontaCateter_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - CLASSIFICAÇÃO ISOLADO 2", "ColetaPontaCateter_" + i + "_tipoIsolado2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaPontaCateter_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaPontaCateter_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta Swab Anal
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - DATA DA COLETA", "ColetaSwabAnal_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - NÚMERO DE ISOLADOS", "ColetaSwabAnal_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - ESPÉCIE ISOLADA", "ColetaSwabAnal_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - CLASSIFICAÇÃO ISOLADO", "ColetaSwabAnal_" + i + "_tipoIsolado"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - PERFIL RESISTÊNCIA", "ColetaSwabAnal_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - MECANISMO RESISTÊNCIA", "ColetaSwabAnal_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - INFECÇÃO MISTA", "ColetaSwabAnal_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - 2ª ESPÉCIE ISOLADA", "ColetaSwabAnal_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - CLASSIFICAÇÃO ISOLADO 2", "ColetaSwabAnal_" + i + "_tipoIsolado2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaSwabAnal_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaSwabAnal_" + i + "_mecanismoResistenciaEspecie2"));
        }

        //Analise uso medicação
        for (int i = 0; i < NeonatoMapper.numeroAnalisesInfeccoes; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO ATB PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_usoAtbPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO ATF PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_usoAtfPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO ATB APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_usoAtbApos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO ATF APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_usoAtfApos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO ≥ ATB PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_uso3MaisAtbPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO ≥ ATF PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_uso3MaisAtfPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO ≥ ATB APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_uso3MaisAtbApos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO ≥ ATF APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_uso3MaisAtfApos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP NOMES ATB PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_nomesAtbPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP NOMES ATF PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_nomesAtfPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP NOMES ATB APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_nomesAtbApos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP NOMES ATF APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_nomesAtfApos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS ATB PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisAtbPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS ATF PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisAtfPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS ATB APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisAtbApos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS ATF APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisAtfApos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO AMINOGLICOSÍDEOS", "MedicamentosEpisodiosInfeccao_" + i + "_usoAminoglicosideos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS AMINOGLICOSÍDEOS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisAminoglicosideosPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS AMINOGLICOSÍDEOS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisAminoglicosideosApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO ANSAMICINAS", "MedicamentosEpisodiosInfeccao_" + i + "_usoAnsamicinas"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS ANSAMICINAS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisAnsamicinasPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS ANSAMICINAS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisAnsamicinasApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO BETALACTAMICOS", "MedicamentosEpisodiosInfeccao_" + i + "_usoBetalactamicos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS BETALACTAMICOS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisBetalactamicosPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS BETALACTAMICOS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisBetalactamicosApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO CARBAPENEMICOS", "MedicamentosEpisodiosInfeccao_" + i + "_usoCarbapenemicos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS CARBAPENEMICOS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisCarbapenemicosPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS CARBAPENEMICOS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisCarbapenemicosApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO CEFALOSPORINAS PRIMEIRA GERAÇÃO", "MedicamentosEpisodiosInfeccao_" + i + "_usoCefalosporinasPrimeiraGeracao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS CEFALOSPORINAS PRIMEIRA GERAÇÃO PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisCefalosporinasPrimeiraGeracaoPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS CEFALOSPORINAS PRIMEIRA GERAÇÃO APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisCefalosporinasPrimeiraGeracaoApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO CEFALOSPORINAS SEGUNDA GERAÇÃO", "MedicamentosEpisodiosInfeccao_" + i + "_usoCefalosporinasSegundaGeracao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS CEFALOSPORINAS SEGUNDA GERAÇÃO PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisCefalosporinasSegundaGeracaoPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS CEFALOSPORINAS SEGUNDA GERAÇÃO APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisCefalosporinasSegundaGeracaoApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO CEFALOSPORINAS TERCEIRA GERAÇÃO", "MedicamentosEpisodiosInfeccao_" + i + "_usoCefalosporinasTerceiraGeracao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS CEFALOSPORINAS TERCEIRA GERAÇÃO PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisCefalosporinasTerceiraGeracaoPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS CEFALOSPORINAS TERCEIRA GERAÇÃO APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisCefalosporinasTerceiraGeracaoApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO CEFALOSPORINAS QUARTA GERAÇÃO", "MedicamentosEpisodiosInfeccao_" + i + "_usoCefalosporinasQuartaGeracao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS CEFALOSPORINAS QUARTA GERAÇÃO PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisCefalosporinasQuartaGeracaoPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS CEFALOSPORINAS QUARTA GERAÇÃO APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisCefalosporinasQuartaGeracaoApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO CEFALOSPORINAS QUINTA GERAÇÃO", "MedicamentosEpisodiosInfeccao_" + i + "_usoCefalosporinasQuintaGeracao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS CEFALOSPORINAS QUINTA GERAÇÃO PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisCefalosporinasQuintaGeracaoPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS CEFALOSPORINAS QUINTA GERAÇÃO APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisCefalosporinasQuintaGeracaoApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO GLICILCICLINAS", "MedicamentosEpisodiosInfeccao_" + i + "_usoGlicilciclinas"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS GLICILCICLINAS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisGlicilciclinasPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS GLICILCICLINAS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisGlicilciclinasApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO GLICOPEPTIDEOS", "MedicamentosEpisodiosInfeccao_" + i + "_usoGlicopeptideos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS GLICOPEPTIDEOS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisGlicopeptideosPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS GLICOPEPTIDEOS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisGlicopeptideosApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO INIBIDORES DE FOLATO", "MedicamentosEpisodiosInfeccao_" + i + "_usoInibidoresDeFolato"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS INIBIDORES DE FOLATO PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisInibidoresDeFolatoPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS INIBIDORES DE FOLATO APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisInibidoresDeFolatoApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO LINCOSAMIDAS", "MedicamentosEpisodiosInfeccao_" + i + "_usoLincosamidas"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS LINCOSAMIDAS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisLincosamidasPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS LINCOSAMIDAS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisLincosamidasApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO MACROLIDEOS", "MedicamentosEpisodiosInfeccao_" + i + "_usoMacrolideos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS MACROLIDEOS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisMacrolideosPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS MACROLIDEOS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisMacrolideosApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO NITROFURANICOS", "MedicamentosEpisodiosInfeccao_" + i + "_usoNitrofuranicos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS NITROFURANICOS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisNitrofuranicosPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS NITROFURANICOS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisNitrofuranicosApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO NITROIMIDAZOIS", "MedicamentosEpisodiosInfeccao_" + i + "_usoNitroimidazois"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS NITROIMIDAZOIS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisNitroimidazoisPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS NITROIMIDAZOIS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisNitroimidazoisApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO POLIMIXINAS", "MedicamentosEpisodiosInfeccao_" + i + "_usoPolimixinas"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS POLIMIXINAS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisPolimixinasPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS POLIMIXINAS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisPolimixinasApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO QUINOLONAS", "MedicamentosEpisodiosInfeccao_" + i + "_usoQuinolonas"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS QUINOLONAS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisQuinolonasPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS QUINOLONAS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisQuinolonasApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO TETRACICLINAS", "MedicamentosEpisodiosInfeccao_" + i + "_usoTetraciclinas"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS TETRACICLINAS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisTetraciclinasPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS TETRACICLINAS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisTetraciclinasApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO AZOIS", "MedicamentosEpisodiosInfeccao_" + i + "_usoAzois"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS AZOIS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisAzoisPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS AZOIS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisAzoisApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO EQUINOCANDINAS", "MedicamentosEpisodiosInfeccao_" + i + "_usoEquinocandinas"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS EQUINOCANDINAS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisEquinocandinasPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS EQUINOCANDINAS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisEquinocandinasApos"));

            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP USO POLIENOS", "MedicamentosEpisodiosInfeccao_" + i + "_usoPolienos"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS POLIENOS PRÉVIO", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisPolienosPrevio"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP DIAS POLIENOS APÓS", "MedicamentosEpisodiosInfeccao_" + i + "_diasTotaisPolienosApos"));

        }

        return columnMappings;
    }


    public static List<ExcelColumnMapping> createIsoladosColumnMapping() {

        List<ExcelColumnMapping> columnMappings = new ArrayList<>();

        // Propriedades gerais
        columnMappings.add(new ExcelColumnMapping("ANO", "ano"));
        columnMappings.add(new ExcelColumnMapping("PRONTUÁRIO", "prontuario"));
        columnMappings.add(new ExcelColumnMapping("PACIENTE", "paciente"));
        columnMappings.add(new ExcelColumnMapping("DATA DE NASCIMENTO", "dataNascimento"));
        columnMappings.add(new ExcelColumnMapping("DATA DE INTERNAÇÃO", "dataInternacao"));
        columnMappings.add(new ExcelColumnMapping("DATA DO DESFECHO", "dataDesfecho"));
        columnMappings.add(new ExcelColumnMapping("DIAS DE INTERNAÇÃO", "diasInternacao"));
        columnMappings.add(new ExcelColumnMapping("DESFECHO", "obito"));
        columnMappings.add(new ExcelColumnMapping("PROCEDÊNCIA", "localNascimentoCodigo"));
        columnMappings.add(new ExcelColumnMapping("INDICAÇÃO DE INTERNAÇÃO", "motivoInternacaoCodigo"));
        columnMappings.add(new ExcelColumnMapping("SEXO AO NASCIMENTO", "sexoCodigo"));
        columnMappings.add(new ExcelColumnMapping("PESO AO NASCER", "pesoGramas"));
        columnMappings.add(new ExcelColumnMapping("CLASSIFICAÇÃO PESO", "pesoNascimentoCodigo"));
        columnMappings.add(new ExcelColumnMapping("CLASSIFICAÇÃO IDADE GESTACIONAL", "idadeGestacionalCodigo"));
        columnMappings.add(new ExcelColumnMapping("TIPO DE PARTO", "tipoPartoCodigo"));
        columnMappings.add(new ExcelColumnMapping("BOLSA ROTA", "roturaMembranaCodigo"));
        columnMappings.add(new ExcelColumnMapping("APGAR 1", "apgar1"));
        columnMappings.add(new ExcelColumnMapping("APGAR 5", "apgar5"));
        //columnMappings.add(new ExcelColumnMapping("RISCO INFECCIOSO", "riscoInfeccioso"));
        //columnMappings.add(new ExcelColumnMapping("SEPSE CLÍNICA", "sepseClinica"));

        // Propriedades adicionais
        columnMappings.add(new ExcelColumnMapping("DATA DA COLETA", "dataColeta"));
        columnMappings.add(new ExcelColumnMapping("SÍTIO DE COLETA", "sitioColetaCodigo"));
        columnMappings.add(new ExcelColumnMapping("AGENTE", "agenteCodigo"));
        columnMappings.add(new ExcelColumnMapping("CLASSIFICAÇÃO DO AGENTE", "classificacaoAgenteCodigo"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A 3 OU MAIS DROGAS", "resistencia3OuMaisDrogas"));
        columnMappings.add(new ExcelColumnMapping("MECANISMO DE RESISTÊNCIA", "mecanismoResistencia"));
        columnMappings.add(new ExcelColumnMapping("PERFIL DE RESISTÊNCIA", "perfilResistencia"));

        // Classe antimicrobianos
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A AMINOGLICOSÍDEOS", "resistenciaAminoglicosideos"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A ANSAMICINAS", "resistenciaAnsamicinas"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A BETALACTÂMICOS", "resistenciaBetalactamicos"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CARBAPENÊMICOS", "resistenciaCarbapenemicos"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFALOSPORINAS 1ª GERAÇÃO", "resistenciaCefalosporinasPrimeiraGeracao"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFALOSPORINAS 2ª GERAÇÃO", "resistenciaCefalosporinasSegundaGeracao"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFALOSPORINAS 3ª GERAÇÃO", "resistenciaCefalosporinasTerceiraGeracao"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFALOSPORINAS 4ª GERAÇÃO", "resistenciaCefalosporinasQuartaGeracao"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFALOSPORINAS 5ª GERAÇÃO", "resistenciaCefalosporinasQuintaGeracao"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A GLICILCICLINAS", "resistenciaGlicilciclinas"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A GLICOPEPTÍDEOS", "resistenciaGlicopeptideos"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A INIBIDORES DE FOLATO", "resistenciaInibidoresFolato"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A LINCOSAMIDAS", "resistenciaLincosamidas"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A MACROLÍDEOS", "resistenciaMacrolideos"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A NITROFURÂNICOS", "resistenciaNitrofuranicos"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A NITROIMIDAZÓLICOS", "resistenciaNitroimidazolicos"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A POLIMIXINAS", "resistenciaPolimixinas"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A QUINOLONAS", "resistenciaQuinolonas"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A TETRACICLINAS", "resistenciaTetraciclinas"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A AZÓIS", "resistenciaAzois"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A EQUINOCANDINAS", "resistenciaEquinocandinas"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A POLIENOS", "resistenciaPolienos"));

        // Antimicrobianos individuais
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A AMICACINA", "resistenciaAmicacina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A GENTAMICINA", "resistenciaGentamicina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A RIFAMPICINA", "resistenciaRifampicina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A AMPICILINA", "resistenciaAmpicilina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A AMPICILINA-SULBACTAM", "resistenciaAmpicilinaSulbactam"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A AMOXICILINA-ÁCIDO CLAVULÂNICO", "resistenciaAmoxicilinaAcidoClavulanico"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A BENZILPENICILINA", "resistenciaBenzilpenicilina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFOXITINA", "resistenciaCefoxitina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A OXACILINA", "resistenciaOxacilina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A PENICILINA", "resistenciaPenicilina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A PIPERACILINA-TAZOBACTAM", "resistenciaPiperacilinaTazobactam"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A TICARCILINA-ÁCIDO CLAVULÂNICO", "resistenciaTicarcilinaAcidoClavulanico"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A DORIPENEM", "resistenciaDoripenem"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A ERTAPENEM", "resistenciaErtapenem"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A IMIPENEM", "resistenciaImipenem"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A MEROPENEM", "resistenciaMeropenem"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFALEXINA", "resistenciaCefalexina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFADROXIL", "resistenciaCefadroxil"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFALOTINA", "resistenciaCefalotina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFAZOLINA", "resistenciaCefazolina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFUROXIMA", "resistenciaCefuroxima"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFACLOR", "resistenciaCefaclor"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFTRIAXONA", "resistenciaCeftriaxona"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFTAXIMA", "resistenciaCeftaxima"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFTAZIDIMA", "resistenciaCeftazidima"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFEPIMA", "resistenciaCefepima"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CEFPIROMA", "resistenciaCefpiroma"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A KEFAZOL", "resistenciaKefazol"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A TIGECICLINA", "resistenciaTigeciclina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A VANCOMICINA", "resistenciaVancomicina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A TEICOPLANINA", "resistenciaTeicoplanina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A TELAVANCINA", "resistenciaTelavancina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A SULFAZOTRIM", "resistenciaSulfazotrim"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A TRIMETOPRIMA-SULFAMETOXAZOL", "resistenciaTrimetoprimaSulfametoxazol"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CLINDAMICINA", "resistenciaClindamicina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A AZITROMICINA", "resistenciaAzitromicina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A ERITROMICINA", "resistenciaEritromicina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A NITROFURANTOÍNA", "resistenciaNitrofurantoina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A METRONIDAZOL", "resistenciaMetronidazol"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A COLISTINA", "resistenciaColistina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A POLIMIXINA B", "resistenciaPolimixinaB"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CIPROFLOXACINA", "resistenciaCiprofloxacina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A LEVOFLOXACINA", "resistenciaLevofloxacina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A MOXIFLOXACINA", "resistenciaMoxifloxacina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A NORFLOXACINA", "resistenciaNorfloxacina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A TETRACICLINA", "resistenciaTetraciclina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A FLUCONAZOL", "resistenciaFluconazol"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A VORICONAZOL", "resistenciaVoriconazol"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CETOCONAZOL", "resistenciaCetoconazol"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A MICONAZOL", "resistenciaMiconazol"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A CAPSOFUNGINA", "resistenciaCapsofungina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A MICAFUNGINA", "resistenciaMicafungina"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A ANFOTERICINA B", "resistenciaAnfotericinaB"));
        columnMappings.add(new ExcelColumnMapping("RESISTÊNCIA A NISTATINA", "resistenciaNistatina"));

        return columnMappings;
    }

}
