package com.roderly.microbiomelabufu.paciente.model;

import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
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
@Table(name = "foto_perfil")
@Entity(name = "FotoPerfil")
public class FotoPerfil extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_foto_perfil;
    private String nome_arquivo;
    private String caminho_arquivo;
    private Long tamanho;
    private String formato;
    private LocalDateTime data_upload;
    private Boolean is_active;

    @OneToOne
    @JoinColumn(name = "id_paciente", unique = true)
    private Paciente paciente;
}
