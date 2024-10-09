package com.roderly.pesquisaneonatos.cadastros_gerais.perfil_resistencia_microorganismo.model;

import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfil_resistencia_microorganismo")
public class PerfilResistenciaMicroorganismo extends EntidadeRastreada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_resistencia_microorganismo")
    private Long idPerfilResistenciaMicroorganismo;

    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    public PerfilResistenciaMicroorganismo(Long idPerfilResistenciaMicroorganismo) {
        this.idPerfilResistenciaMicroorganismo = idPerfilResistenciaMicroorganismo;
    }
}
