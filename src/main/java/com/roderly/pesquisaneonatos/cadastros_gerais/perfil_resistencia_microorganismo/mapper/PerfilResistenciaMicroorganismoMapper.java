package com.roderly.pesquisaneonatos.cadastros_gerais.perfil_resistencia_microorganismo.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.perfil_resistencia_microorganismo.model.PerfilResistenciaMicroorganismo;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class PerfilResistenciaMicroorganismoMapper {

    public static ListagemCadastroResponse listar(PerfilResistenciaMicroorganismo response) {
        return new ListagemCadastroResponse(
                response.getIdPerfilResistenciaMicroorganismo(),
                response.getCodigo(),
                response.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(response.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(response.getAlteradoEm()),
                response.getIsActive()
        );
    }


    public static CadastroResponse response(PerfilResistenciaMicroorganismo response) {
        return new CadastroResponse(
                response.getIdPerfilResistenciaMicroorganismo(),
                response.getDescricao(),
                response.getCodigo(),
                response.getIsActive()
        );
    }


    public static PerfilResistenciaMicroorganismo save(CadastroRequest request) {
        var data = new PerfilResistenciaMicroorganismo();

        data.setIdPerfilResistenciaMicroorganismo(request.id());
        data.setCodigo(request.codigo());
        data.setDescricao(request.descricao());
        data.setIsActive(request.isActive());

        return data;
    }


}
