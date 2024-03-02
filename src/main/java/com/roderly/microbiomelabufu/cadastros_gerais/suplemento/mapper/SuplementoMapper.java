package com.roderly.microbiomelabufu.cadastros_gerais.suplemento.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.request.SuplementoIngredienteRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.request.SuplementoRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response.SuplementoIngredienteListagem;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response.SuplementoIngredienteResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response.SuplementoListagemResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response.SuplementoResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.model.Ingrediente;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.model.Suplemento;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.model.SuplementoIngrediente;

public class SuplementoMapper {

    public static Suplemento suplementoRequestToSuplemento(SuplementoRequest request) {
        var suplemento = new Suplemento();
        suplemento.setId_suplemento(request.id_suplemento());
        suplemento.setNome(request.nome());
        suplemento.setDescricao(request.descricao());
        suplemento.setDosagem(request.dosagem());
        suplemento.setInstrucoes_uso(request.instrucoes_uso());
        suplemento.setIs_active(request.is_active());

        return suplemento;
    }


    public static SuplementoIngrediente suplementoIgredienteRequestToSuplementoIgrediente(SuplementoIngredienteRequest request, Suplemento suplemento) {
        var suplementoIgrediente = new SuplementoIngrediente();
        var ingrediente = request.id_ingrediente() != null ? new Ingrediente(request.id_ingrediente()) : null;

        suplementoIgrediente.setId_suplemento_ingrediente(request.id_suplemento_ingrediente());
        suplementoIgrediente.setDosagem(request.dosagem());
        suplementoIgrediente.setIs_active(request.is_active());
        suplementoIgrediente.setSuplemento(suplemento);
        suplementoIgrediente.setIngrediente(ingrediente);

        return suplementoIgrediente;
    }


    public static SuplementoIngredienteResponse suplementoIngredienteToSuplementoIngredienteResponse(SuplementoIngrediente suplementoIngrediente) {
        var idIngrediente = suplementoIngrediente.getIngrediente() != null ? suplementoIngrediente.getIngrediente().getId_ingrediente() : null;

        return new SuplementoIngredienteResponse(
                suplementoIngrediente.getId_suplemento_ingrediente(),
                suplementoIngrediente.getDosagem(),
                suplementoIngrediente.getIs_active(),
                idIngrediente
        );
    }


    public static SuplementoResponse suplementoTosuplementoResponse(Suplemento suplemento) {
        return new SuplementoResponse(
                suplemento.getId_suplemento(),
                suplemento.getNome(),
                suplemento.getDescricao(),
                suplemento.getDosagem(),
                suplemento.getInstrucoes_uso(),
                suplemento.getIs_active()
        );
    }


    public static SuplementoListagemResponse suplementoToSuplementoIngredienteListagem(Suplemento suplemento) {
        var ingredientes = suplemento.getSuplementoIngredientes().stream()
                .map(SuplementoMapper::suplementoIngredienteToSuplementoIngredienteListagem)
                .toList();

        return new SuplementoListagemResponse(
                suplemento.getId_suplemento(),
                suplemento.getNome(),
                suplemento.getDescricao(),
                suplemento.getDosagem(),
                suplemento.getInstrucoes_uso(),
                suplemento.getIs_active(),
                ingredientes
        );
    }


    public static SuplementoIngredienteListagem suplementoIngredienteToSuplementoIngredienteListagem(SuplementoIngrediente suplementoIngrediente) {
        var ingrediente = suplementoIngrediente.getIngrediente() != null ? suplementoIngrediente.getIngrediente().getNome() : "";

        return new SuplementoIngredienteListagem(
                ingrediente,
                suplementoIngrediente.getDosagem()
        );
    }
}
