package com.roderly.microbiomelabufu.login.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity(name = "permissao")
@Table(name = "permissao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_permissao")

public class Permissao implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_permissao;

    private String descricao;

    @Override
    public String getAuthority() {
        return descricao;
    }

}
