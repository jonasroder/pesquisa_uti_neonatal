package com.roderly.pesquisaneonatos.cadastros_gerais.sexo.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.sexo.model.Sexo;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class SexoMapper {

    public static ListagemCadastroResponse listar(Sexo response) {
        return new ListagemCadastroResponse(
                response.getIdSexo(),
                response.getCodigo(),
                response.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(response.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(response.getAlteradoEm()),
                response.getIsActive()
        );
    }


    public static CadastroResponse response(Sexo response) {
        return new CadastroResponse(
                response.getIdSexo(),
                response.getDescricao(),
                response.getCodigo(),
                response.getIsActive()
        );
    }


    public static Sexo save(CadastroRequest request) {
        var data = new Sexo();

        data.setIdSexo(request.id());
        data.setCodigo(request.codigo());
        data.setDescricao(request.descricao());
        data.setIsActive(request.isActive());

        return data;
    }


}
