package com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.dto.request.ClassificacaoMicroorganismoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.dto.response.ClassificacaoMicroorganismoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.model.ClassificacaoMicroorganismo;
import com.roderly.pesquisaneonatos.cadastros_gerais.genero.model.Genero;
import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.model.Microorganismo;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class ClassificacaoMicroorganismoMapper {

    public static ListagemCadastroResponse classificacaoMicroorganismoToListagemCadastroResponse(ClassificacaoMicroorganismo classificacaoMicroorganismo) {
        return new ListagemCadastroResponse(
                classificacaoMicroorganismo.getIdClassificacaoMicroorganismo(),
                classificacaoMicroorganismo.getCodigo(),
                classificacaoMicroorganismo.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(classificacaoMicroorganismo.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(classificacaoMicroorganismo.getAlteradoEm()),
                classificacaoMicroorganismo.getIsActive()
        );
    }


    public static ClassificacaoMicroorganismoResponse classificacaoMicrorganismoToClassisficacaoMicroorganismoRepsponse(ClassificacaoMicroorganismo classificacaoMicroorganismo) {
        return new ClassificacaoMicroorganismoResponse(
                classificacaoMicroorganismo.getIdClassificacaoMicroorganismo(),
                classificacaoMicroorganismo.getDescricao(),
                classificacaoMicroorganismo.getCodigo(),
                classificacaoMicroorganismo.getIsActive()
        );
    }


    public static ClassificacaoMicroorganismo classificacaoMicroorganismoRequestToClassificacaoMicroorganismo(ClassificacaoMicroorganismoRequest request) {

        var classificacaoMicroorganismo = new ClassificacaoMicroorganismo();
        classificacaoMicroorganismo.setIdClassificacaoMicroorganismo(request.idClassificacaoMicroorganismo());
        classificacaoMicroorganismo.setCodigo(request.codigo());
        classificacaoMicroorganismo.setDescricao(request.descricao());
        classificacaoMicroorganismo.setIsActive(request.isActive());

        return classificacaoMicroorganismo;
    }


}
