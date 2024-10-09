package com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.model;


import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classificacao_microorganismo")
public class ClassificacaoMicroorganismo extends EntidadeRastreada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classificacao_microorganismo")
    private Long idClassificacaoMicroorganismo;

    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    public ClassificacaoMicroorganismo(Long idClassificacaoMicroorganismo) {
        this.idClassificacaoMicroorganismo = idClassificacaoMicroorganismo;
    }
}
