package com.roderly.pesquisaneonatos.cadastros_gerais.via_administracao.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.tipo_parto.model.TipoParto;
import com.roderly.pesquisaneonatos.cadastros_gerais.via_administracao.model.ViaAdministracao;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class ViaAdministracaoMapper {

    public static ListagemCadastroResponse listar(ViaAdministracao response) {
        return new ListagemCadastroResponse(
                response.getIdViaAdministracao(),
                response.getCodigo(),
                response.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(response.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(response.getAlteradoEm()),
                response.getIsActive()
        );
    }


    public static CadastroResponse response(ViaAdministracao response) {
        return new CadastroResponse(
                response.getIdViaAdministracao(),
                response.getDescricao(),
                response.getCodigo(),
                response.getIsActive()
        );
    }


    public static ViaAdministracao save(CadastroRequest request) {
        var data = new ViaAdministracao();

        data.setIdViaAdministracao(request.id());
        data.setCodigo(request.codigo());
        data.setDescricao(request.descricao());
        data.setIsActive(request.isActive());

        return data;
    }


}
