package com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.dto.request.TipoInformacaoSaudeRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.dto.resopnse.TipoInformacaoSaudeResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.model.TipoInformacaoSaude;

public class TipoInformacaoSaudeMapper {

    public static TipoInformacaoSaude tipoInformacaoSaudeRequestToTipoInformacaoSaude(TipoInformacaoSaudeRequest request){
        var infoSaude = new TipoInformacaoSaude();
        infoSaude.setId_tipo_informacao_saude(request.id_tipo_informacao_saude());
        infoSaude.setNome(request.nome());
        infoSaude.setDescricao(request.descricao());
        infoSaude.setIs_active(request.is_active());

        return infoSaude;
    }


    public static TipoInformacaoSaudeResponse tipoInformacaoSaudeTotipoInformacaoSaudeResponse(TipoInformacaoSaude tipoInformacaoSaude){
        return new TipoInformacaoSaudeResponse(
                tipoInformacaoSaude.getId_tipo_informacao_saude(),
                tipoInformacaoSaude.getNome(),
                tipoInformacaoSaude.getDescricao(),
                tipoInformacaoSaude.getIs_active()
        );
    }

}
