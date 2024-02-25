package com.roderly.microbiomelabufu.cadastros_gerais.uf.model;

import com.roderly.microbiomelabufu.paciente.model.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "uf")
@Entity(name = "UF")
public class UF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_uf;
    private String sigla;
    private String descricao;
    private Boolean is_active;

    @OneToMany(mappedBy = "uf")
    private Set<Endereco> enderecos = new HashSet<>();


    public UF(Long id_uf) {
        this.id_uf = id_uf;
    }
}
