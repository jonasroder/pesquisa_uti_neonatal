package com.roderly.microbiomelabufu.paciente.model;

import com.roderly.microbiomelabufu.cadastros_gerais.sexo.model.Sexo;
import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "neonato")
public class Neonato extends EntidadeRastreada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_neonato")
    private Long idNeonato;

    @Column(name = "nome_mae")
    private String nomeMae;

    @Column(name = "prontuario")
    private String prontuario;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "data_internacao")
    private LocalDate dataInternacao;

    @Column(name = "data_desfecho")
    private LocalDate dataDesfecho;

    @Column(name = "obito")
    private Boolean obito;

    @Column(name = "peso")
    private Long peso;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sexo", referencedColumnName = "id_sexo")
    private Sexo sexo;


    public Neonato(Long idNeonato) {
        this.idNeonato = idNeonato;
    }

}
