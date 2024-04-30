package com.roderly.microbiomelabufu.common.persistense;

import com.roderly.microbiomelabufu.usuario.model.Usuario;
import jakarta.persistence.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class EntidadeRastreada {

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "alterado_em")
    private LocalDateTime alteradoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alterado_por_id")
    private Usuario alteradoPor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criado_por_id", updatable = false)
    private Usuario criadoPor;

    @Column(name = "is_active")
    private Boolean isActive;

    @PrePersist
    protected void aoCriar() {
        criadoEm = LocalDateTime.now();
        criadoPor = getUsuarioAutenticado();
        isActive = true;
    }

    @PreUpdate
    protected void aoAtualizar() {
        alteradoEm = LocalDateTime.now();
        alteradoPor = getUsuarioAutenticado();
    }

    private Usuario getUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
            return (Usuario) authentication.getPrincipal();
        }
        return null;
    }
}
