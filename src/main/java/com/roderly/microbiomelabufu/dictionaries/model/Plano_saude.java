package com.roderly.microbiomelabufu.dictionaries.model;

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
public class Plano_saude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_plano_saude;
    private String descricao;
    private boolean is_active;
}
