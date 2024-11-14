package com.roderly.pesquisaneonatos.neonato.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColetaEpisodio {
    String dataInfeccao;
    int numeroIsolados;
    Long especieIsolada;
    Long perfilResistencia;
    Long mecanismoResistencia;
    int infeccaoMista;
    Long especieIsolada2;
    Long perfilResistenciaEspecie2;
    Long mecanismoResistenciaEspecie2;
}