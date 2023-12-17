package com.roderly.microbiomelabufu.login.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity(name = "Permissao_usuario")
@Table(name = "permissao_usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_permissao_usuario")

public class Permissao_usuario implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_permissao_usuario;

    private String descricao;

    @Override
    public String getAuthority() {
        return descricao;
    }

}
