package com.roderly.pesquisaneonatos.login.model;

import com.roderly.pesquisaneonatos.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private boolean rememberMe;

    public RefreshToken(String token, Usuario usuario, Instant expiresAt, boolean rememberMe) {
        this.token = token;
        this.usuario = usuario;
        this.expiresAt = expiresAt;
        this.createdAt = Instant.now();
        this.rememberMe = rememberMe;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }
}
