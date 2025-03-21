package com.roderly.pesquisaneonatos.cadastros_gerais.sitio_malformacao.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.sitio_malformacao.model.SitioMalformacao;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class SitioMalformacaoMapper {

    public static ListagemCadastroResponse listar(SitioMalformacao response) {
        return new ListagemCadastroResponse(
                response.getIdSitioMalformacao(),
                response.getCodigo(),
                response.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(response.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(response.getAlteradoEm()),
                response.getIsActive()
        );
    }


    public static CadastroResponse response(SitioMalformacao response) {
        return new CadastroResponse(
                response.getIdSitioMalformacao(),
                response.getDescricao(),
                response.getCodigo(),
                response.getIsActive()
        );
    }


    public static SitioMalformacao save(CadastroRequest request) {
        var data = new SitioMalformacao();

        data.setIdSitioMalformacao(request.id());
        data.setCodigo(request.codigo());
        data.setDescricao(request.descricao());
        data.setIsActive(request.isActive());

        return data;
    }


}
