package com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.model;

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
public class MotivoInternacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_motivo_internacao")
    private Long idMotivoInternacao;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "motivoInternacao")
    private Set<Neonato> neonatoSet;

    public MotivoInternacao(Long idMotivoInternacao) {
        this.idMotivoInternacao = idMotivoInternacao;
    }
}
