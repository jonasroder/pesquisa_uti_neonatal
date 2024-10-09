package com.roderly.pesquisaneonatos.cadastros_gerais.resistencia_microorganismo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "resistencia_microorganismo")
public class ResistenciaMicroorganismo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resistencia_microorganismo")
    private Long idResistenciaMicroorganismo;

    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "criado_por_id")
    private Long criadoPorId;

    @Column(name = "alterado_em")
    private LocalDateTime alteradoEm;

    @Column(name = "alterado_por_id")
    private Long alteradoPorId;

    @Column(name = "is_active")
    private Boolean isActive;

    public ResistenciaMicroorganismo(Long idResistenciaMicroorganismo) {
        this.idResistenciaMicroorganismo = idResistenciaMicroorganismo;
    }
}
