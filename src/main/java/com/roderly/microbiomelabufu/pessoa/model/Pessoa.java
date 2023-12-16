package com.roderly.microbiomelabufu.pessoa.model;

import com.roderly.microbiomelabufu.endereco.model.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "pessoa")
@Entity(name = "Pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pessoa;

    private Long id_tipo_pessoa;

    private String nome;

    private String sobrenome;

    private Long id_sexo;

    private LocalDate data_nascimento;

    private String telefone;

    private String email;

    private Long id_estado_civil;

    private Long id_profissao;

    private String cpf;

    private Long id_etnia;

    private Long id_escolaridade;

    private Long id_religiao;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> endereco = new ArrayList<>();


}
