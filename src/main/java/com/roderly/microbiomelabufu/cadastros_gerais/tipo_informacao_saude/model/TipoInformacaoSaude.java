package com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.model;

import com.roderly.microbiomelabufu.consulta.model.ConsultaDiagnostico;
import com.roderly.microbiomelabufu.consulta.model.ConsultaInformacaoSaude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Table(name = "tipo_informacao_saude")
@Entity(name = "TipoInformacaoSaude")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoInformacaoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipo_informacao_saude;
    private String nome;
    private String descricao;
    private Boolean is_active;

    @OneToMany(mappedBy = "tipoInformacaoSaude")
    private Set<ConsultaInformacaoSaude> consultaInformacoesSaude;


    public TipoInformacaoSaude(Long id_tipo_informacao_saude) {
        this.id_tipo_informacao_saude = id_tipo_informacao_saude;
    }

}
