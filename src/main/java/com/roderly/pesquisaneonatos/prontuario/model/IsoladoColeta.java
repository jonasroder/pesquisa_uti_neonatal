package com.roderly.pesquisaneonatos.prontuario.model;

import com.roderly.pesquisaneonatos.cadastros_gerais.mecanismo_resistencia_microorganismo.model.MecanismoResistenciaMicroorganismo;
import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.model.Microorganismo;
import com.roderly.pesquisaneonatos.cadastros_gerais.perfil_resistencia_microorganismo.model.PerfilResistenciaMicroorganismo;
import com.roderly.pesquisaneonatos.common.persistense.EntidadeRastreada;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "isolado_coleta")
public class IsoladoColeta extends EntidadeRastreada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_isolado_coleta")
    private Long idIsoladoColeta;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_evento", referencedColumnName = "id_evento")
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_microorganismo", referencedColumnName = "id_microorganismo", nullable = false)
    private Microorganismo microorganismo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_perfil_resistencia_microorganismo", referencedColumnName = "id_perfil_resistencia_microorganismo", nullable = false)
    private PerfilResistenciaMicroorganismo perfilResistenciaMicroorganismo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_mecanismo_resistencia_microorganismo", referencedColumnName = "id_mecanismo_resistencia_microorganismo", nullable = false)
    private MecanismoResistenciaMicroorganismo mecanismoResistenciaMicroorganismo;

    @Column(name = "desconsiderar_coleta")
    private Boolean desconsiderarColeta = true;

    @OneToMany(mappedBy = "isoladoColeta", fetch = FetchType.LAZY)
    private List<AntibiogramaIsolado> antibiogramasIsolado = new ArrayList<>();

    public IsoladoColeta(Long idIsoladoColeta) {
        this.idIsoladoColeta = idIsoladoColeta;
    }
}
