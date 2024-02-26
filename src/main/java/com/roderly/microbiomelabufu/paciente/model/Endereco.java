package com.roderly.microbiomelabufu.paciente.model;

import com.roderly.microbiomelabufu.cadastros_gerais.etnia.model.Etnia;
import com.roderly.microbiomelabufu.cadastros_gerais.uf.model.UF;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
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
    private String cep;
    private String referencia;

    @ManyToOne
    @JoinColumn(name = "id_uf", referencedColumnName = "id_uf")
    private UF uf;

    @OneToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    private Paciente paciente;
}