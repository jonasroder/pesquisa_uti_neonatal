package com.roderly.pesquisaneonatos.cadastros_gerais.rotura_membrana.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.rotura_membrana.model.RoturaMembrana;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class RoturaMembranaMapper {

    public static ListagemCadastroResponse listar(RoturaMembrana response) {
        return new ListagemCadastroResponse(
                response.getIdRoturaMembrana(),
                response.getCodigo(),
                response.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(response.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(response.getAlteradoEm()),
                response.getIsActive()
        );
    }


    public static CadastroResponse response(RoturaMembrana response) {
        return new CadastroResponse(
                response.getIdRoturaMembrana(),
                response.getDescricao(),
                response.getCodigo(),
                response.getIsActive()
        );
    }


    public static RoturaMembrana save(CadastroRequest request) {
        var data = new RoturaMembrana();

        data.setIdRoturaMembrana(request.id());
        data.setCodigo(request.codigo());
        data.setDescricao(request.descricao());
        data.setIsActive(request.isActive());

        return data;
    }


}
