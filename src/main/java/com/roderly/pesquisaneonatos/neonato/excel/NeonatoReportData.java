package com.roderly.pesquisaneonatos.neonato.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NeonatoReportData {
    Integer ano;
    String nomeMae;
    String prontuario;
    String dataNascimento;
    String dataInternacao;
    String dataDesfecho;
    Integer diasInternacao;
    Long obito;
    Long riscoInfeccio;
    Long sepseClinica;
    Long apgar1;
    Long apgar5;
    Long pesoGramas;
    Long sexoCodigo;
    Long causaObitoCodigo;
    Long pesoNascimentoCodigo;
    Long localNascimentoCodigo;
    Long motivoInternacaoCodigo;
    Long idadeGestacionalCodigo;
    Long tipoPartoCodigo;
    Long roturaMembranaCodigo;
    Long malformacao;
    Long sitioMalformacaoCodigo;
    Long cirurgia;
    Long sitioCirurgiaCodigo;
}
