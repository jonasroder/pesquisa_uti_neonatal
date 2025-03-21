package com.roderly.pesquisaneonatos.cadastros_gerais.local_nascimento.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.local_nascimento.model.LocalNascimento;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class LocalNascimentoMapper {

    public static ListagemCadastroResponse listar(LocalNascimento response) {
        return new ListagemCadastroResponse(
                response.getIdLocalNascimento(),
                response.getCodigo(),
                response.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(response.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(response.getAlteradoEm()),
                response.getIsActive()
        );
    }


    public static CadastroResponse response(LocalNascimento response) {
        return new CadastroResponse(
                response.getIdLocalNascimento(),
                response.getDescricao(),
                response.getCodigo(),
                response.getIsActive()
        );
    }


    public static LocalNascimento save(CadastroRequest request) {
        var data = new LocalNascimento();

        data.setIdLocalNascimento(request.id());
        data.setCodigo(request.codigo());
        data.setDescricao(request.descricao());
        data.setIsActive(request.isActive());

        return data;
    }


}
