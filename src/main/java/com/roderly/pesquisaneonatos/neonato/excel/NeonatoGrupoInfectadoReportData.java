package com.roderly.pesquisaneonatos.neonato.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NeonatoGrupoInfectadoReportData {
    Integer ano;
    String prontuario;
    String paciente;
    String dataNascimento;
    String dataInternacao;
    String dataDesfecho;
    Long diasInternacao;
    Long obito;
    Long localNascimentoCodigo;
    Long motivoInternacaoCodigo;
    Long sexoCodigo;
    Long pesoGramas;
    Long pesoNascimentoCodigo;
    Long idadeGestacionalCodigo;
    Long tipoPartoCodigo;
    Long roturaMembranaCodigo;
    Long apgar1;
    Long apgar5;

    Long malformacao;
    Long sitioMalformacao1;
    Long sitioMalformacao2;
    Long sitioMalformacao3;
    Long sitioMalformacao4;
    Long sitioMalformacao5;

    Long cirurgia;
    Long cirurgiaPrevia;
    Long sitioCirurgia1;
    String dataCirurgia1;
    Long sitioCirurgia2;
    String dataCirurgia2;
    Long sitioCirurgia3;
    String dataCirurgia3;
    Long sitioCirurgia4;
    String dataCirurgia4;
    Long sitioCirurgia5;
    String dataCirurgia5;
    Long sitioCirurgia6;
    String dataCirurgia6;
    Long sitioCirurgia7;
    String dataCirurgia7;
    Long sitioCirurgia8;
    String dataCirurgia8;
    Long sitioCirurgia9;
    String dataCirurgia9;
    Long sitioCirurgia10;
    String dataCirurgia10;

    int usoFlebotomia;
    int diasTotaisFlebotomia;
    int diasFlebotomia1Infencao;

    int usoCvu;
    int diasTotaisCvu;
    int diasCvu1Infencao;

    int usoPicc;
    int diasTotaisPicc;
    int diasPicc1Infencao;

    int usoEntubacao;
    int diasTotaisEntubacao;
    int diasEntubacao1Infencao;

    int usoSondaVesical;
    int diasTotaisSondaVesical;
    int diasSondaVesical1Infencao;

    int usoNutricaoParenteral;
    int diasTotaisNutricaoParenteral;
    int diasNutricaoParenteral1Infencao;

    int usoDreno;
    int diasTotaisDreno;
    int diasDreno1Infencao;


}
