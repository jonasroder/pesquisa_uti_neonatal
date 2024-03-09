package com.roderly.microbiomelabufu.cadastros_gerais.suplemento.model;


import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.model.Ingrediente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "suplemento_ingrediente")
@Entity(name = "SuplementoIngrediente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuplementoIngrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_suplemento_ingrediente;
    private String dosagem;
    private Boolean is_active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ingrediente")
    private Ingrediente ingrediente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_suplemento")
    private Suplemento suplemento;

    public SuplementoIngrediente(Long id_suplemento_ingrediente){
        this.id_suplemento_ingrediente = id_suplemento_ingrediente;
    }
}
