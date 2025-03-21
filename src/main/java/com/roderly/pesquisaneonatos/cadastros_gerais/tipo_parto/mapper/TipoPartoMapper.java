package com.roderly.pesquisaneonatos.cadastros_gerais.tipo_parto.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.tipo_parto.model.TipoParto;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class TipoPartoMapper {

    public static ListagemCadastroResponse listar(TipoParto response) {
        return new ListagemCadastroResponse(
                response.getIdTipoParto(),
                response.getCodigo(),
                response.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(response.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(response.getAlteradoEm()),
                response.getIsActive()
        );
    }


    public static CadastroResponse response(TipoParto response) {
        return new CadastroResponse(
                response.getIdTipoParto(),
                response.getDescricao(),
                response.getCodigo(),
                response.getIsActive()
        );
    }


    public static TipoParto save(CadastroRequest request) {
        var data = new TipoParto();

        data.setIdTipoParto(request.id());
        data.setCodigo(request.codigo());
        data.setDescricao(request.descricao());
        data.setIsActive(request.isActive());

        return data;
    }


}
