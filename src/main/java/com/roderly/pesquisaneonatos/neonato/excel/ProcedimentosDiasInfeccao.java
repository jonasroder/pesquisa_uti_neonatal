package com.roderly.pesquisaneonatos.neonato.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcedimentosDiasInfeccao {
    Integer uso;
    Integer diasTotaisUso;

    Integer diasAteInfecao1;
    Integer diasAteInfecao2;
    Integer diasAteInfecao3;
    Integer diasAteInfecao4;
    Integer diasAteInfecao5;
    Integer diasAteInfecao6;
    Integer diasAteInfecao7;

    Integer diasAposInfecao1;
    Integer diasAposInfecao2;
    Integer diasAposInfecao3;
    Integer diasAposInfecao4;
    Integer diasAposInfecao5;
    Integer diasAposInfecao6;
    Integer diasAposInfecao7;

    public void setDiasAteInfecao(int episodio, int dias) {
        switch (episodio) {
            case 1 -> this.diasAteInfecao1 = dias;
            case 2 -> this.diasAteInfecao2 = dias;
            case 3 -> this.diasAteInfecao3 = dias;
            case 4 -> this.diasAteInfecao4 = dias;
            case 5 -> this.diasAteInfecao5 = dias;
            case 6 -> this.diasAteInfecao6 = dias;
            case 7 -> this.diasAteInfecao7 = dias;
        }
    }

    public void setDiasAposInfecao(int episodio, int dias) {
        switch (episodio) {
            case 1 -> this.diasAposInfecao1 = dias;
            case 2 -> this.diasAposInfecao2 = dias;
            case 3 -> this.diasAposInfecao3 = dias;
            case 4 -> this.diasAposInfecao4 = dias;
            case 5 -> this.diasAposInfecao5 = dias;
            case 6 -> this.diasAposInfecao6 = dias;
            case 7 -> this.diasAposInfecao7 = dias;
        }
    }
}
