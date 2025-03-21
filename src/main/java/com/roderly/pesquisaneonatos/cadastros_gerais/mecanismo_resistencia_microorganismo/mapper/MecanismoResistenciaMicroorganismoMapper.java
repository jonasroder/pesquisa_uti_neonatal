package com.roderly.pesquisaneonatos.cadastros_gerais.mecanismo_resistencia_microorganismo.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.mecanismo_resistencia_microorganismo.model.MecanismoResistenciaMicroorganismo;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class MecanismoResistenciaMicroorganismoMapper {

    public static ListagemCadastroResponse listar(MecanismoResistenciaMicroorganismo response) {
        return new ListagemCadastroResponse(
                response.getIdMecanismoResistenciaMicroorganismo(),
                response.getCodigo(),
                response.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(response.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(response.getAlteradoEm()),
                response.getIsActive()
        );
    }


    public static CadastroResponse response(MecanismoResistenciaMicroorganismo response) {
        return new CadastroResponse(
                response.getIdMecanismoResistenciaMicroorganismo(),
                response.getDescricao(),
                response.getCodigo(),
                response.getIsActive()
        );
    }


    public static MecanismoResistenciaMicroorganismo save(CadastroRequest request) {
        var data = new MecanismoResistenciaMicroorganismo();

        data.setIdMecanismoResistenciaMicroorganismo(request.id());
        data.setCodigo(request.codigo());
        data.setDescricao(request.descricao());
        data.setIsActive(request.isActive());

        return data;
    }


}
