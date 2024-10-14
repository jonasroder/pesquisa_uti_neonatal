package com.roderly.pesquisaneonatos.prontuario.model;

import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.model.Antimicrobiano;
import com.roderly.pesquisaneonatos.cadastros_gerais.resistencia_microorganismo.model.ResistenciaMicroorganismo;
import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "antibiograma_isolado")
public class AntibiogramaIsolado extends EntidadeRastreada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_antibiograma_isolado")
    private Long idAntibiogramaIsolado;

    // Relacionamento com IsoladoColeta
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_isolado_coleta", referencedColumnName = "id_isolado_coleta", nullable = false)
    private IsoladoColeta isoladoColeta;

    // Relacionamento com Antimicrobiano
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_antimicrobiano", referencedColumnName = "id_antimicrobiano", nullable = false)
    private Antimicrobiano antimicrobiano;

    // Relacionamento com ResistenciaMicroorganismo
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_resistencia_microorganismo", referencedColumnName = "id_resistencia_microorganismo", nullable = false)
    private ResistenciaMicroorganismo resistenciaMicroorganismo;


    public AntibiogramaIsolado(Long idAntibiogramaIsolado) {
        this.idAntibiogramaIsolado = idAntibiogramaIsolado;
    }
}
