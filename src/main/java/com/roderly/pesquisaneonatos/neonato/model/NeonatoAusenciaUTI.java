package com.roderly.pesquisaneonatos.neonato.model;


import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "neonato_ausencia_uti")
public class NeonatoAusenciaUTI extends EntidadeRastreada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_neonato_ausencia_uti")
    private Long idNeonatoAusenciaUti;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_neonato", referencedColumnName = "id_neonato", nullable = false)
    private Neonato neonato;

    @Column(name = "data_saida_uti", nullable = false)
    private LocalDate dataSaidaUti;

    @Column(name = "data_retorno_uti")
    private LocalDate dataRetornoUti;

    public NeonatoAusenciaUTI(Long idNeonatoAusenciaUti) {
        this.idNeonatoAusenciaUti = idNeonatoAusenciaUti;
    }
}