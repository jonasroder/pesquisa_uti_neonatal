package com.roderly.microbiomelabufu.pessoa.model;

import com.roderly.microbiomelabufu.endereco.model.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "pessoa")
@Entity(name = "Pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pessoa;

    private Integer id_tipo_pessoa;

    private String nome;

    private String sobrenome;

    private Integer id_sexo;

    private LocalDate data_nascimento;

    private String telefone;

    private String email;

    private Integer id_estado_civil;

    private Integer id_profissao;

    private String cpf;

    private Integer id_etnia;

    private Integer id_escolaridade;

    private Integer id_religiao;


    @Transient
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();


}
