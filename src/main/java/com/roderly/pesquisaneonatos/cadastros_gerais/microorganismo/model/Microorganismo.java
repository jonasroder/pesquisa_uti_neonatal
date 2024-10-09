package com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.model;


import com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.model.ClassificacaoMicroorganismo;
import com.roderly.pesquisaneonatos.cadastros_gerais.genero.model.Genero;
import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "microorganismo")

public class Microorganismo extends EntidadeRastreada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_microorganismo")
    private Long idMicroorganismo;

    @Column(name = "codigo", nullable = false)
    private Long codigo;

    // Relacionamento com Genero
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
    private Genero genero;

    // Relacionamento com ClassificacaoMicroorganismo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classificacao_microorganismo", referencedColumnName = "id_classificacao_microorganismo")
    private ClassificacaoMicroorganismo classificacaoMicroorganismo;

    @Column(name = "especie")
    private String especie;

    @Column(name = "acronimo")
    private String acronimo;

    public Microorganismo(Long idMicroorganismo) {
        this.idMicroorganismo = idMicroorganismo;
    }
}
