package com.roderly.pesquisaneonatos.login.model;


import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import com.roderly.pesquisaneonatos.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login_log")
public class LoginLog extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_login_log")
    private Long idLoginLog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "status", nullable = false)
    private String status; // ex: SUCCESS, FAILURE, BLOCKED

    @Column(name = "motivo")
    private String motivo; // ex: senha incorreta, conta inativa

    @Column(name = "user_agent")
    private String userAgent; // ex: informações do navegador/dispositivo
}