package com.roderly.microbiomelabufu.common.persistense;

import com.roderly.microbiomelabufu.usuario.model.Usuario;
import jakarta.persistence.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;


@MappedSuperclass
public abstract class EntidadeRastreada {

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criado_em;
    private LocalDateTime alterado_em;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alterado_por_id")
    private Usuario alterado_por_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criado_por_id", updatable = false)
    private Usuario criado_por_id;


    @PrePersist
    protected void aoCriar() {
        criado_em = LocalDateTime.now();
        criado_por_id = getUsuarioAutenticado();
    }

    @PreUpdate
    protected void aoAtualizar() {
        alterado_em = LocalDateTime.now();
        alterado_por_id = getUsuarioAutenticado();
    }

    private Usuario getUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
            return (Usuario) authentication.getPrincipal();
        }
        return null;
    }
}
