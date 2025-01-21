package com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.model;

import com.roderly.pesquisaneonatos.cadastros_gerais.sistema_fisiologico.model.SistemaFisiologico;
import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "motivo_internacao")
public class MotivoInternacao extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_motivo_internacao")
    private Long idMotivoInternacao;

    @ManyToOne
    @JoinColumn(name = "codigo", referencedColumnName = "codigo", nullable = false)
    private SistemaFisiologico sistemaFisiologico;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "motivoInternacao")
    private Set<Neonato> neonatoSet;

    public MotivoInternacao(Long idMotivoInternacao) {
        this.idMotivoInternacao = idMotivoInternacao;
    }
}
