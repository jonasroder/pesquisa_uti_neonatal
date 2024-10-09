package com.roderly.pesquisaneonatos.prontuario.model;

import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.model.Antimicrobiano;
import com.roderly.pesquisaneonatos.cadastros_gerais.resistencia_microorganismo.model.ResistenciaMicroorganismo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "antibiograma_isolado")
public class AntibiogramaIsolado {

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

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "criado_por_id")
    private Long criadoPorId;

    @Column(name = "alterado_em")
    private LocalDateTime alteradoEm;

    @Column(name = "alterado_por_id")
    private Long alteradoPorId;

    @Column(name = "is_active")
    private Boolean isActive;

    public AntibiogramaIsolado(Long idAntibiogramaIsolado) {
        this.idAntibiogramaIsolado = idAntibiogramaIsolado;
    }
}
