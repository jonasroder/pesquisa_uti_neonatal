package com.roderly.microbiomelabufu.formulario_atividade_fisica.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "formulario_atividade_fisica")
@Entity(name = "FormularioAtividadeFisica")
public class FormularioAtividadeFisica extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_formulario_atividade_fisica;

    private LocalTime tempo_atividade_vigorosa;

    private Integer dias_atividade_vigorosa;

    private LocalTime tempo_atividade_moderada;

    private Integer dias_atividade_moderada;

    private LocalTime tempo_caminhada_moderada;

    private Integer dias_caminhada_moderada;

    private LocalTime tempo_sentado_dia_util;

    private LocalTime tempo_sentado_fds;

    @OneToOne
    @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
    private Consulta consulta;
}
