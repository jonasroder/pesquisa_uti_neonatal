package com.roderly.microbiomelabufu.endereco.model;

import com.roderly.microbiomelabufu.pessoa.model.Pessoa;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "endereco")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_endereco;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String id_uf;

    private String cep;

    private String referencia;


    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
    private Pessoa pessoa;

}
