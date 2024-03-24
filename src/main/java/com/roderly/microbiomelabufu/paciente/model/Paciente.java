package com.roderly.microbiomelabufu.paciente.model;

import com.roderly.microbiomelabufu.cadastros_gerais.escolaridade.model.Escolaridade;
import com.roderly.microbiomelabufu.cadastros_gerais.estado_civil.model.EstadoCivil;
import com.roderly.microbiomelabufu.cadastros_gerais.etnia.model.Etnia;
import com.roderly.microbiomelabufu.cadastros_gerais.plano_saude.model.PlanoSaude;
import com.roderly.microbiomelabufu.cadastros_gerais.profissao.model.Profissao;
import com.roderly.microbiomelabufu.cadastros_gerais.religiao.model.Religiao;
import com.roderly.microbiomelabufu.cadastros_gerais.sexo.model.Sexo;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.model.Suplemento;
import com.roderly.microbiomelabufu.common.persistense.EntidadeRastreada;
import com.roderly.microbiomelabufu.consulta.model.Consulta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paciente")
@Entity(name = "Paciente")
public class Paciente extends EntidadeRastreada {
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
    private String numero_plano_saude;
    private Long num_visitas;
    private String indicacao;
    private String observacao;

    @OneToOne(mappedBy = "paciente")
    private Endereco endereco;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profissao", referencedColumnName = "id_profissao")
    private Profissao profissao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plano_saude", referencedColumnName = "id_plano_saude")
    private PlanoSaude planoSaude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id_estado_civil")
    private EstadoCivil estadoCivil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sexo", referencedColumnName = "id_sexo")
    private Sexo sexo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etnia", referencedColumnName = "id_etnia")
    private Etnia etnia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_escolaridade", referencedColumnName = "id_escolaridade")
    private Escolaridade escolaridade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_religiao", referencedColumnName = "id_religiao")
    private Religiao religiao;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private FotoPerfil fotoPerfil;


    public Paciente(Long id_paciente) {
        this.id_paciente = id_paciente;
    }

}
