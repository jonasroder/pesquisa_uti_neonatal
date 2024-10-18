package com.roderly.pesquisaneonatos.cadastros_gerais.via_administracao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "via_administracao")
public class ViaAdministracao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_via_administracao")
    private Long idViaAdministracao;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    public ViaAdministracao(Long idViaAdministracao) {
        this.idViaAdministracao = idViaAdministracao;
    }
}
