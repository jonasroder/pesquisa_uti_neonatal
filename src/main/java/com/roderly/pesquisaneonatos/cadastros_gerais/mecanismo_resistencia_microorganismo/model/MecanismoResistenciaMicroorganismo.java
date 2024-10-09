package com.roderly.pesquisaneonatos.cadastros_gerais.mecanismo_resistencia_microorganismo.model;


import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mecanismo_resistencia_microorganismo")
public class MecanismoResistenciaMicroorganismo extends EntidadeRastreada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mecanismo_resistencia_microorganismo")
    private Long idMecanismoResistenciaMicroorganismo;

    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    public MecanismoResistenciaMicroorganismo(Long idMecanismoResistenciaMicroorganismo) {
        this.idMecanismoResistenciaMicroorganismo = idMecanismoResistenciaMicroorganismo;
    }
}
