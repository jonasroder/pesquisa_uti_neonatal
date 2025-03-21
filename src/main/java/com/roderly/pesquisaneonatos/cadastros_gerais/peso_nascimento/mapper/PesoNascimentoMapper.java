package com.roderly.pesquisaneonatos.cadastros_gerais.peso_nascimento.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.peso_nascimento.model.PesoNascimento;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class PesoNascimentoMapper {

    public static ListagemCadastroResponse listar(PesoNascimento response) {
        return new ListagemCadastroResponse(
                response.getIdPesoNascimento(),
                response.getCodigo(),
                response.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(response.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(response.getAlteradoEm()),
                response.getIsActive()
        );
    }


    public static CadastroResponse response(PesoNascimento response) {
        return new CadastroResponse(
                response.getIdPesoNascimento(),
                response.getDescricao(),
                response.getCodigo(),
                response.getIsActive()
        );
    }


    public static PesoNascimento save(CadastroRequest request) {
        var data = new PesoNascimento();

        data.setIdPesoNascimento(request.id());
        data.setCodigo(request.codigo());
        data.setDescricao(request.descricao());
        data.setIsActive(request.isActive());

        return data;
    }


}
