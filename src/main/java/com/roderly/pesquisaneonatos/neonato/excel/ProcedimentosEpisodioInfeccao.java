package com.roderly.pesquisaneonatos.neonato.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcedimentosEpisodioInfeccao {
    String data;
    Long sitio;
    Long microorganismo;
    Long classificacaoMicroorganismo;
    Long perfilResistenciaMicroorganismo;
    Long mecanismoResistenciaMicroorganismo;
    Integer InfeccaoMista;
    Long microorganismo2;
    Long classificacaoMicroorganismo2;
    Long perfilResistenciaMicroorganismo2;
    Long mecanismoResistenciaMicroorganismo2;
    Integer diasFlebotomiaAte;
    Integer diasFlebotomiaApos;
    Integer diasCvuAte;
    Integer diasCvuApos;
    Integer diasPiccAte;
    Integer diasPiccApos;
    Integer diasEntubacaoAte;
    Integer diasEntubacaoApos;
    Integer diasSondaVesicalAte;
    Integer diasSondaVesicalApos;
    Integer diasNutricaoParenteralAte;
    Integer diasNutricaoParenteralApos;
    Integer diasDrenoAte;
    Integer diasDrenoApos;
    Integer diasIntracathAte;
    Integer diasIntracathApos;
    Integer diasCvcDuploLumenAte;
    Integer diasCvcDuploLumenApos;
}
