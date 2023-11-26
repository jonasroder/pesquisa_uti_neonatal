package com.roderly.microbiomelabufu.login.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Entity(name = "Usuario")
@Table(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_usuario")

public class UsuarioModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    private int id_pessoa;

    private String usuario;

    private String senha;

    private Integer id_situacao;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_permissao",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_permissao")
    )

    private Collection<Permissao> permissoes;

    public UsuarioModel(int id_pessoa, String usuario, String encryptedPassword, Integer id_situacao) {
        this.id_pessoa = id_pessoa;
        this.usuario = usuario;
        this.senha = encryptedPassword;
        this.id_situacao = id_situacao != null ? id_situacao : 1;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissoes.stream()
                .map(permissao -> new SimpleGrantedAuthority(permissao.getDescricao()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
