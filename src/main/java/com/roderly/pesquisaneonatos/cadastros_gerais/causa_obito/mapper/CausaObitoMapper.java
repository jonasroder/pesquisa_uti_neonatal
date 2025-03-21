package com.roderly.pesquisaneonatos.cadastros_gerais.causa_obito.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.causa_obito.model.CausaObito;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class CausaObitoMapper {

    public static ListagemCadastroResponse listar(CausaObito response) {
        return new ListagemCadastroResponse(
                response.getIdCausaObito(),
                response.getCodigo(),
                response.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(response.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(response.getAlteradoEm()),
                response.getIsActive()
        );
    }


    public static CadastroResponse response(CausaObito response) {
        return new CadastroResponse(
                response.getIdCausaObito(),
                response.getDescricao(),
                response.getCodigo(),
                response.getIsActive()
        );
    }


    public static CausaObito save(CadastroRequest request) {
        var data = new CausaObito();

        data.setIdCausaObito(request.id());
        data.setCodigo(request.codigo());
        data.setDescricao(request.descricao());
        data.setIsActive(request.isActive());

        return data;
    }


}
