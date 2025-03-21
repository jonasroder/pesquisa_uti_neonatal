package com.roderly.pesquisaneonatos.cadastros_gerais.idade_gestacional.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.idade_gestacional.model.IdadeGestacional;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class IdadeGestacionalMapper {

    public static ListagemCadastroResponse listar(IdadeGestacional response) {
        return new ListagemCadastroResponse(
                response.getIdIdadeGestacional(),
                response.getCodigo(),
                response.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(response.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(response.getAlteradoEm()),
                response.getIsActive()
        );
    }


    public static CadastroResponse response(IdadeGestacional response) {
        return new CadastroResponse(
                response.getIdIdadeGestacional(),
                response.getDescricao(),
                response.getCodigo(),
                response.getIsActive()
        );
    }


    public static IdadeGestacional save(CadastroRequest request) {
        var data = new IdadeGestacional();

        data.setIdIdadeGestacional(request.id());
        data.setCodigo(request.codigo());
        data.setDescricao(request.descricao());
        data.setIsActive(request.isActive());

        return data;
    }


}
