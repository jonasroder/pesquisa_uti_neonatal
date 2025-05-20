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

    UsoProcedimentos Cirurgia;
    List<DataCodigoCirurgia> ColunasCirurgia;

    UsoProcedimentos Flebotomia;
    UsoProcedimentos Cvu;
    UsoProcedimentos Picc;
    UsoProcedimentos Entubacao;
    UsoProcedimentos SondaVesical;
    UsoProcedimentos NutricaoParenteral;
    UsoProcedimentos Dreno;
    UsoProcedimentos Intracath;
    UsoProcedimentos CvcDuploLumen;

    List<ProcedimentosEpisodioInfeccao> ProcedimentosEpisodioInfeccao;

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
