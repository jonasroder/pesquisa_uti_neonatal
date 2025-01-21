package com.roderly.pesquisaneonatos.neonato.model;

import com.roderly.pesquisaneonatos.cadastros_gerais.causa_obito.model.CausaObito;
import com.roderly.pesquisaneonatos.cadastros_gerais.idade_gestacional.model.IdadeGestacional;
import com.roderly.pesquisaneonatos.cadastros_gerais.local_nascimento.model.LocalNascimento;
import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.model.MotivoInternacao;
import com.roderly.pesquisaneonatos.cadastros_gerais.peso_nascimento.model.PesoNascimento;
import com.roderly.pesquisaneonatos.cadastros_gerais.rotura_membrana.model.RoturaMembrana;
import com.roderly.pesquisaneonatos.cadastros_gerais.sexo.model.Sexo;
import com.roderly.pesquisaneonatos.cadastros_gerais.tipo_parto.model.TipoParto;
import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import com.roderly.pesquisaneonatos.prontuario.model.Evento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "neonato")
public class Neonato extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_neonato")
    private Long idNeonato;

    @Column(name = "nome_mae")
    private String nomeMae;

    @Column(name = "prontuario")
    private String prontuario;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "data_internacao")
    private LocalDate dataInternacao;

    @Column(name = "data_desfecho")
    private LocalDate dataDesfecho;

    @Column(name = "obito")
    private Boolean obito;

    @Column(name = "gemelar")
    private Boolean gemelar;

    @Column(name = "risco_infeccioso")
    private Boolean riscoInfeccio;

    @Column(name = "sepse_clinica")
    private Boolean sepseClinica;

    @Column(name = "apgar_1")
    private Long apgar1;

    @Column(name = "apgar_5")
    private Long apgar5;

    @Column(name = "peso_gramas")
    private Long pesoGramas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sexo", referencedColumnName = "id_sexo")
    private Sexo sexo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_causa_obito", referencedColumnName = "id_causa_obito")
    private CausaObito causaObito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_peso_nascimento", referencedColumnName = "id_peso_nascimento")
    private PesoNascimento pesoNascimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_local_nascimento", referencedColumnName = "id_local_nascimento")
    private LocalNascimento localNascimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_motivo_internacao", referencedColumnName = "id_motivo_internacao")
    private MotivoInternacao motivoInternacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_idade_gestacional", referencedColumnName = "id_idade_gestacional")
    private IdadeGestacional idadeGestacional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_parto", referencedColumnName = "id_tipo_parto")
    private TipoParto tipoParto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rotura_membrana", referencedColumnName = "id_rotura_membrana")
    private RoturaMembrana roturaMembrana;




    @OneToMany(mappedBy = "neonato", fetch = FetchType.LAZY)
    private List<Evento> eventoList = new ArrayList<>();

    @OneToMany(mappedBy = "neonato", fetch = FetchType.LAZY)
    private List<NeonatoSitioMalformacao> neonatoMalformacaoList = new ArrayList<>();

    @OneToMany(mappedBy = "neonato", fetch = FetchType.LAZY)
    private List<NeonatoAusenciaUTI> neonatoAusenciaUtiList = new ArrayList<>();

    public Neonato(Long idNeonato) {
        this.idNeonato = idNeonato;
    }
}
