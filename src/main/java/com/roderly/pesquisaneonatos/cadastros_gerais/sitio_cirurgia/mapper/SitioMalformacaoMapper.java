package com.roderly.pesquisaneonatos.cadastros_gerais.sitio_cirurgia.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.sitio_cirurgia.model.SitioCirurgia;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class SitioMalformacaoMapper {

    public static ListagemCadastroResponse listar(SitioCirurgia response) {
        return new ListagemCadastroResponse(
                response.getIdSitioCirurgia(),
                response.getCodigo(),
                response.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(response.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(response.getAlteradoEm()),
                response.getIsActive()
        );
    }


    public static CadastroResponse response(SitioCirurgia response) {
        return new CadastroResponse(
                response.getIdSitioCirurgia(),
                response.getDescricao(),
                response.getCodigo(),
                response.getIsActive()
        );
    }


    public static SitioCirurgia save(CadastroRequest request) {
        var data = new SitioCirurgia();

        data.setIdSitioCirurgia(request.id());
        data.setCodigo(request.codigo());
        data.setDescricao(request.descricao());
        data.setIsActive(request.isActive());

        return data;
    }


}
