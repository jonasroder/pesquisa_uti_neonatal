package com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.dto.request.DiagonosticoRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.dto.response.DiagnosticoResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.model.Diagnostico;

public class DiagnosticoMapper {

    public static Diagnostico diangnosticoRequestToDiagnostico(DiagonosticoRequest request){
        var diagnostico = new Diagnostico();

        diagnostico.setId_diagnostico(request.id_diagnostico());
        diagnostico.setNome(request.nome());
        diagnostico.setDescricao(request.descricao());
        diagnostico.setCodigo_cid(request.codigo_cid());
        diagnostico.setIs_active(request.is_active());

        return diagnostico;
    }


    public static DiagnosticoResponse diagnosticoToDiagnosticoReponse(Diagnostico diagnostico){
        return new DiagnosticoResponse(
              diagnostico.getId_diagnostico(),
              diagnostico.getNome(),
              diagnostico.getDescricao(),
              diagnostico.getCodigo_cid(),
              diagnostico.getIs_active()
        );
    }
}
