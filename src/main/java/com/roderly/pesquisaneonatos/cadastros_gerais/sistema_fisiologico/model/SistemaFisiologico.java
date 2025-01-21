package com.roderly.pesquisaneonatos.cadastros_gerais.sistema_fisiologico.model;

import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
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
@Table(name = "sistema_fisiologico")
public class SistemaFisiologico extends EntidadeRastreada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sistema_fisiologico")
    private Long idSistemaFisiologico;

    @Column(name = "codigo", nullable = false, unique = true)
    private Long codigo;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    public SistemaFisiologico(Long idSistemaFisiologico) {
        this.idSistemaFisiologico = idSistemaFisiologico;
    }
}
