package com.roderly.microbiomelabufu.pessoa.model;

import com.roderly.microbiomelabufu.endereco.model.EnderecoModel;
import com.roderly.microbiomelabufu.pessoa.dto.SavePessoaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "pessoa")
@Entity(name = "Pessoa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaModel {
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


    @Setter
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoModel> enderecos = new ArrayList<>();



    public PessoaModel(SavePessoaDTO dados){
        this.id_pessoa = dados.id_pessoa();
        this.id_tipo_pessoa = dados.id_tipo_pessoa();
        this.nome = dados.nome();
        this.sobrenome = dados.sobrenome();
        this.id_sexo = dados.id_sexo();
        this.data_nascimento = dados.data_nascimento();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.id_estado_civil = dados.id_estado_civil();
        this.id_profissao = dados.id_profissao();
        this.cpf = dados.cpf();
        this.id_etnia = dados.id_etnia();
        this.id_escolaridade = dados.id_escolaridade();
        this.id_religiao = dados.id_religiao();
    }

}
