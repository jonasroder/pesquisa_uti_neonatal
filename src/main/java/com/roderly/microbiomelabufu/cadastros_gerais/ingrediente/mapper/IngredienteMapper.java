package com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.dto.request.IngredienteRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.dto.response.IngredienteResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.model.Ingrediente;

public class IngredienteMapper {

    public static Ingrediente ingredienteRequestToIngrediente(IngredienteRequest request){
        var ingrediente = new Ingrediente();
        ingrediente.setId_ingrediente(request.id_ingrediente());
        ingrediente.setDescricao(request.descricao());
        ingrediente.setNome(request.nome());
        ingrediente.setIs_active(request.is_active());

        return ingrediente;
    }


    public static IngredienteResponse ingredienteToIngredienteResponse(Ingrediente ingrediente){
        return new IngredienteResponse(
                ingrediente.getId_ingrediente(),
                ingrediente.getNome(),
                ingrediente.getDescricao(),
                ingrediente.getIs_active()
        );
    }
}
