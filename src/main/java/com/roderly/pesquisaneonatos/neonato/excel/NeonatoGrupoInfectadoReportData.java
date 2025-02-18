package com.roderly.pesquisaneonatos.neonato.excel;

import com.roderly.pesquisaneonatos.cadastros_gerais.sitio_malformacao.model.SitioMalformacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


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
    List<SitioMalformacao> SitioMalformacoes;

    ProcedimentosDiasInfeccao Cirurgia;
    List<DataCodigoCirurgia> ColunasCirurgia;

    ProcedimentosDiasInfeccao Flebotomia;
    ProcedimentosDiasInfeccao Cvu;
    ProcedimentosDiasInfeccao Picc;
    ProcedimentosDiasInfeccao Entubacao;
    ProcedimentosDiasInfeccao SondaVesical;
    ProcedimentosDiasInfeccao NutricaoParenteral;
    ProcedimentosDiasInfeccao Dreno;
    ProcedimentosDiasInfeccao Intracath;
    ProcedimentosDiasInfeccao CvcDuploLumen;

    int riscoInfeccioso;
    int sepseClinica;

    List<ColetaEpisodio> ColetaSangue;
    List<ColetaEpisodio> ColetaSecOcular;
    List<ColetaEpisodio> ColetaLiqPleural;
    List<ColetaEpisodio> ColetaLCR;
    List<ColetaEpisodio> ColetaLiqAscitico;
    List<ColetaEpisodio> ColetaUrina;
    List<ColetaEpisodio> ColetaSecrecaoPulmonar;
    List<ColetaEpisodio> ColetaPontaCateter;
    List<ColetaEpisodio> ColetaSwabAnal;

    List<MedicamentosDiasInfeccao> MedicamentosEpisodiosInfeccao;
}
