package com.roderly.microbiomelabufu.usuario.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeRastreada implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    private String usuario;
    private String senha;
    private String nome_completo;
    private String titulo;
    private String especialidade;
    private String telefone_1;
    private String telefone_2;
    private String cpf;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<UsuarioRole> usuarioRoles;



    public Usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return usuarioRoles.stream()
//                .filter(UsuarioRole::getIs_active)
//                .map(usuarioRole -> new SimpleGrantedAuthority(usuarioRole.getRole().getNome()))
//                .collect(Collectors.toSet());
//    }

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
