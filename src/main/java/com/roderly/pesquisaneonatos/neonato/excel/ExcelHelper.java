package com.roderly.pesquisaneonatos.neonato.excel;

import com.roderly.pesquisaneonatos.common.excel.ExcelColumnMapping;

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
        for (int i = 1; i < 8; i++) {
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
        for (int i = 1; i < 8; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS FLEBOTOMIAS ATÉ A " + i + "ª INFECÇÃO", "Flebotomia_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS FLEBOTOMIAS APÓS A " + i + "ª INFECÇÃO", "Flebotomia_diasAposInfecao" + i));
        }

        //CVU
        columnMappings.add(new ExcelColumnMapping("USO CVU", "Cvu_uso"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS CVU", "Cvu_diasTotaisUso"));
        for (int i = 1; i < 8; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS CVU ATÉ A " + i + "ª INFECÇÃO", "Cvu_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS CVU APÓS A " + i + "ª INFECÇÃO", "Cvu_diasAposInfecao" + i));
        }

        //Picc
        columnMappings.add(new ExcelColumnMapping("USO PICC", "Picc_uso"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS PICC", "Picc_diasTotaisUso"));
        for (int i = 1; i < 8; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS PICC ATÉ A " + i + "ª INFECÇÃO", "Picc_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS PICC APÓS A " + i + "ª INFECÇÃO", "Picc_diasAposInfecao" + i));
        }

        //Entubacao
        columnMappings.add(new ExcelColumnMapping("USO ENTUBAÇÃO", "Entubacao_uso"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS ENTUBAÇÃO", "Entubacao_diasTotaisUso"));
        for (int i = 1; i < 8; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS ENTUBAÇÃO ATÉ A " + i + "ª INFECÇÃO", "Entubacao_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS ENTUBAÇÃO APÓS A " + i + "ª INFECÇÃO", "Entubacao_diasAposInfecao" + i));
        }

        //Sonda Vesical
        columnMappings.add(new ExcelColumnMapping("USO SONDA VESICAL", "SondaVesical_uso"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS SONDA VESICAL", "SondaVesical_diasTotaisUso"));
        for (int i = 1; i < 8; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS SONDA VESICAL ATÉ A " + i + "ª INFECÇÃO", "SondaVesical_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS SONDA VESICAL APÓS A " + i + "ª INFECÇÃO", "SondaVesical_diasAposInfecao" + i));
        }

        //Nutrição parenteral
        columnMappings.add(new ExcelColumnMapping("USO NUTRIÇÃO PARENTERAL", "NutricaoParenteral_uso"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS NUTRIÇÃO PARENTERAL", "NutricaoParenteral_diasTotaisUso"));
        for (int i = 1; i < 8; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS NUTRIÇÃO PARENTERAL ATÉ A " + i + "ª INFECÇÃO", "NutricaoParenteral_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS NUTRIÇÃO PARENTERAL APÓS A " + i + "ª INFECÇÃO", "NutricaoParenteral_diasAposInfecao" + i));
        }

        //Nutrição parenteral
        columnMappings.add(new ExcelColumnMapping("USO DRENO", "Dreno_uso"));
        columnMappings.add(new ExcelColumnMapping("DIAS TOTAIS DRENO", "Dreno_diasTotaisUso"));
        for (int i = 1; i < 8; i++) {
            columnMappings.add(new ExcelColumnMapping("DIAS DRENO ATÉ A " + i + "ª INFECÇÃO", "Dreno_diasAteInfecao" + i));
            columnMappings.add(new ExcelColumnMapping("DIAS DRENO APÓS A " + i + "ª INFECÇÃO", "Dreno_diasAposInfecao" + i));
        }

        columnMappings.add(new ExcelColumnMapping("SEPSE CLÍNICA", "sepseClinica"));
        columnMappings.add(new ExcelColumnMapping("RISCO INFECCIOSO", "riscoInfeccioso"));

        // Coleta Sangue
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - DATA DA COLETA", "ColetaSangue_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - NÚMERO DE ISOLADOS", "ColetaSangue_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - ESPÉCIE ISOLADA", "ColetaSangue_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - PERFIL RESISTÊNCIA", "ColetaSangue_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - MECANISMO RESISTÊNCIA", "ColetaSangue_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - INFECÇÃO MISTA", "ColetaSangue_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - 2ª ESPÉCIE ISOLADA", "ColetaSangue_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaSangue_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SANGUE - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaSangue_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta Secrecao Ocular
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - DATA DA COLETA", "ColetaSecOcular_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - NÚMERO DE ISOLADOS", "ColetaSecOcular_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - ESPÉCIE ISOLADA", "ColetaSecOcular_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - PERFIL RESISTÊNCIA", "ColetaSecOcular_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - MECANISMO RESISTÊNCIA", "ColetaSecOcular_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - INFECÇÃO MISTA", "ColetaSecOcular_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - 2ª ESPÉCIE ISOLADA", "ColetaSecOcular_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaSecOcular_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SEC OCULAR - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaSecOcular_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta Liquido Pleural
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - DATA DA COLETA", "ColetaLiqPleural_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - NÚMERO DE ISOLADOS", "ColetaLiqPleural_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - ESPÉCIE ISOLADA", "ColetaLiqPleural_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - PERFIL RESISTÊNCIA", "ColetaLiqPleural_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - MECANISMO RESISTÊNCIA", "ColetaLiqPleural_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - INFECÇÃO MISTA", "ColetaLiqPleural_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - 2ª ESPÉCIE ISOLADA", "ColetaLiqPleural_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaLiqPleural_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ PLEURAL - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaLiqPleural_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta LCR
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - DATA DA COLETA", "ColetaLCR_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - NÚMERO DE ISOLADOS", "ColetaLCR_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - ESPÉCIE ISOLADA", "ColetaLCR_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - PERFIL RESISTÊNCIA", "ColetaLCR_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - MECANISMO RESISTÊNCIA", "ColetaLCR_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - INFECÇÃO MISTA", "ColetaLCR_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - 2ª ESPÉCIE ISOLADA", "ColetaLCR_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaLCR_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LCR - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaLCR_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta LÍQ ASCÍTICO
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - DATA DA COLETA", "ColetaLiqAscitico_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - NÚMERO DE ISOLADOS", "ColetaLiqAscitico_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - ESPÉCIE ISOLADA", "ColetaLiqAscitico_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - PERFIL RESISTÊNCIA", "ColetaLiqAscitico_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - MECANISMO RESISTÊNCIA", "ColetaLiqAscitico_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - INFECÇÃO MISTA", "ColetaLiqAscitico_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - 2ª ESPÉCIE ISOLADA", "ColetaLiqAscitico_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaLiqAscitico_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA LÍQ ASCÍTICO - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaLiqAscitico_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta Urina
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - DATA DA COLETA", "ColetaUrina_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - NÚMERO DE ISOLADOS", "ColetaUrina_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - ESPÉCIE ISOLADA", "ColetaUrina_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - PERFIL RESISTÊNCIA", "ColetaUrina_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - MECANISMO RESISTÊNCIA", "ColetaUrina_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - INFECÇÃO MISTA", "ColetaUrina_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - 2ª ESPÉCIE ISOLADA", "ColetaUrina_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaUrina_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA URINA - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaUrina_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta Secrecao Pulmonar
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - DATA DA COLETA", "ColetaSecrecaoPulmonar_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - NÚMERO DE ISOLADOS", "ColetaSecrecaoPulmonar_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - ESPÉCIE ISOLADA", "ColetaSecrecaoPulmonar_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - PERFIL RESISTÊNCIA", "ColetaSecrecaoPulmonar_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - MECANISMO RESISTÊNCIA", "ColetaSecrecaoPulmonar_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - INFECÇÃO MISTA", "ColetaSecrecaoPulmonar_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - 2ª ESPÉCIE ISOLADA", "ColetaSecrecaoPulmonar_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaSecrecaoPulmonar_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SECREÇÃO PULMONAR - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaSecrecaoPulmonar_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta Ponta Cateter
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - DATA DA COLETA", "ColetaPontaCateter_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - NÚMERO DE ISOLADOS", "ColetaPontaCateter_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - ESPÉCIE ISOLADA", "ColetaPontaCateter_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - PERFIL RESISTÊNCIA", "ColetaPontaCateter_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - MECANISMO RESISTÊNCIA", "ColetaPontaCateter_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - INFECÇÃO MISTA", "ColetaPontaCateter_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - 2ª ESPÉCIE ISOLADA", "ColetaPontaCateter_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaPontaCateter_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA PONTA CATETER - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaPontaCateter_" + i + "_mecanismoResistenciaEspecie2"));
        }

        // Coleta Swab Anal
        for (int i = 0; i < 5; i++) {
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - DATA DA COLETA", "ColetaSwabAnal_" + i + "_dataInfeccao"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - NÚMERO DE ISOLADOS", "ColetaSwabAnal_" + i + "_numeroIsolados"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - ESPÉCIE ISOLADA", "ColetaSwabAnal_" + i + "_especieIsolada"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - PERFIL RESISTÊNCIA", "ColetaSwabAnal_" + i + "_perfilResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - MECANISMO RESISTÊNCIA", "ColetaSwabAnal_" + i + "_mecanismoResistencia"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - INFECÇÃO MISTA", "ColetaSwabAnal_" + i + "_infeccaoMista"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - 2ª ESPÉCIE ISOLADA", "ColetaSwabAnal_" + i + "_especieIsolada2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - PERFIL RESISTÊNCIA ESPÉCIE 2", "ColetaSwabAnal_" + i + "_perfilResistenciaEspecie2"));
            columnMappings.add(new ExcelColumnMapping((i + 1) + " EP COLETA SWAB ANAL - MECANISMO RESISTÊNCIA ESPÉCIE 2", "ColetaSwabAnal_" + i + "_mecanismoResistenciaEspecie2"));
        }


        return columnMappings;
    }
}
