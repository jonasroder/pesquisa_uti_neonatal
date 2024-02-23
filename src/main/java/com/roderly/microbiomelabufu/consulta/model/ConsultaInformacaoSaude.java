package com.roderly.microbiomelabufu.consulta.model;

import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.model.Diagnostico;
import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.model.TipoInformacaoSaude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "consulta_informacao_saude")
@Entity(name = "ConsultaInformacaoSaude")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaInformacaoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_consulta_informacao_saude;
    private String valor;
    private Boolean is_active;

    @ManyToOne
    @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
    private Consulta consulta;

    @ManyToOne
    @JoinColumn(name = "id_tipo_informacao_saude", referencedColumnName = "id_tipo_informacao_saude")
    private TipoInformacaoSaude tipoInformacaoSaude;
}
