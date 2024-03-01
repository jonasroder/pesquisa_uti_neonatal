package com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.model;

import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.model.SuplementoIngrediente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "ingrediente")
@Entity(name = "Ingrediente")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ingrediente;
    private String nome;
    private String descricao;
    private Boolean is_active;

    @OneToMany(mappedBy = "ingrediente")
    private List<SuplementoIngrediente> suplementoIngredientes;

    public Ingrediente(Long id_ingrediente){
        this.id_ingrediente = id_ingrediente;
    }
}
