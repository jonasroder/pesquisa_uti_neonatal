package com.roderly.microbiomelabufu.pessoa.model;

import com.roderly.microbiomelabufu.endereco.model.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "paciente")
@Entity(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paciente;
    private String nome;
    private String sobrenome;
    private LocalDate data_nascimento;
    private String telefone_1;
    private String telefone_2;
    private String email;
    private String cpf;
    private String naturalidade;
    private String nome_pai;
    private String nome_mae;
    private String nome_conjuge;
    private LocalDate data_ultimo_atendimento;
    private Long num_visitas;
    private String indicacao;
    private String observacao;
    private Long id_profissao;
    private Long id_plano_saude;
    private Long id_estado_civil;
    private Long id_sexo;
    private Long id_etnia;
    private Long id_escolaridade;
    private Long id_religiao;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> endereco = new ArrayList<>();

}
