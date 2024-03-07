package com.roderly.microbiomelabufu.cadastros_gerais.problema_entusiasmo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "problema_entusiasmo")
@Entity(name = "ProblemaEntusiasmo")
public class ProblemaEntusiasmo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_problema_entusiasmo;
    private String descricao;
    private Boolean is_active;

    public ProblemaEntusiasmo(Long id_problema_entusiasmo) {
        this.id_problema_entusiasmo = id_problema_entusiasmo;
    }
}
