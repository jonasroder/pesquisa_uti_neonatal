package com.roderly.microbiomelabufu.cadastros_gerais.plano_saude.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "plano_saude")
@Entity(name = "Plano_saude")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_plano_saude;
    private String descricao;
    private boolean is_active;
}
