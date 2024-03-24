package com.roderly.microbiomelabufu.formulario_pittsburgh.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
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
public class ProblemaEntusiasmo extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_problema_entusiasmo;
    private String descricao;
    private Boolean is_active;

    public ProblemaEntusiasmo(Long id_problema_entusiasmo) {
        this.id_problema_entusiasmo = id_problema_entusiasmo;
    }
}
