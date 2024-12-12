package com.roderly.pesquisaneonatos.cadastros_gerais.genero.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.genero.dto.request.GeneroRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.genero.dto.response.GeneroResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.genero.model.Genero;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class GeneroMapper {

    public static ListagemCadastroResponse generoToListagemCadastroResponse(Genero genero) {
        return new ListagemCadastroResponse(
                genero.getIdGenero(),
                genero.getCodigo(),
                genero.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(genero.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(genero.getAlteradoEm()),
                genero.getIsActive()
        );
    }


    public static GeneroResponse generoToGeneroResponse(Genero genero) {
        return new GeneroResponse(
                genero.getIdGenero(),
                genero.getDescricao(),
                genero.getCodigo(),
                genero.getIsActive()
        );
    }


    public static Genero generoRequestToGenero(GeneroRequest request) {
        var genero = new Genero();
        genero.setIdGenero(request.idGenero());
        genero.setCodigo(request.codigo());
        genero.setDescricao(request.descricao());
        genero.setIsActive(request.isActive());

        return genero;
    }


}
