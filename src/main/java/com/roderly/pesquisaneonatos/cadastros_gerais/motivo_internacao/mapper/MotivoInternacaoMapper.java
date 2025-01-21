package com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.dto.request.MotivoInternacaoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.dto.response.MotivoInternacaoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.model.MotivoInternacao;
import com.roderly.pesquisaneonatos.cadastros_gerais.sistema_fisiologico.model.SistemaFisiologico;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.Utilitarios.ObjectUtils;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class MotivoInternacaoMapper {

    public static ListagemCadastroResponse motivoInternacaoToListagemCadastroResponse(MotivoInternacao motivoInternacao) {
        return new ListagemCadastroResponse(
                motivoInternacao.getIdMotivoInternacao(),
                ObjectUtils.safeGet(motivoInternacao.getSistemaFisiologico(), SistemaFisiologico::getCodigo),
                motivoInternacao.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(motivoInternacao.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(motivoInternacao.getAlteradoEm()),
                motivoInternacao.getIsActive()
        );
    }


    public static MotivoInternacaoResponse motivoInternacaoToMotivoInternacaoResponse(MotivoInternacao motivoInternacao) {
        return new MotivoInternacaoResponse(
                motivoInternacao.getIdMotivoInternacao(),
                motivoInternacao.getDescricao(),
                ObjectUtils.safeGet(motivoInternacao.getSistemaFisiologico(), SistemaFisiologico::getIdSistemaFisiologico),
                motivoInternacao.getIsActive()
        );
    }


    public static MotivoInternacao motivoInternacaoRequestToMotivoInternacao(MotivoInternacaoRequest request) {
        var motivoInternacao = new MotivoInternacao(request.idMotivoInternacao());

        motivoInternacao.setIdMotivoInternacao(request.idMotivoInternacao());
        motivoInternacao.setDescricao(request.descricao());
        motivoInternacao.setIsActive(request.isActive());

        return motivoInternacao;
    }


}
