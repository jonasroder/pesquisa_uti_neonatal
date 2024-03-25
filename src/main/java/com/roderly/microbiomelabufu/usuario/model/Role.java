package com.roderly.microbiomelabufu.usuario.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role  extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_role;

    @Column(unique = true)
    private String nome;

    private Boolean is_active;


    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<UsuarioRole> usuarioRoles;
}
