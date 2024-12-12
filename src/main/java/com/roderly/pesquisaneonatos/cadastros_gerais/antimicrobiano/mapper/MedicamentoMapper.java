package com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.dto.request.MedicamentoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.dto.response.MedicamentoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.model.Antimicrobiano;
import com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.model.ClasseAntimicrobiano;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.Utilitarios.ObjectUtils;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class MedicamentoMapper {

    public static ListagemCadastroResponse antimicrobianoToListagemCadastroResponse(Antimicrobiano antimicrobiano) {
        return new ListagemCadastroResponse(
                antimicrobiano.getIdAntimicrobiano(),
                antimicrobiano.getCodigo(),
                antimicrobiano.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(antimicrobiano.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(antimicrobiano.getAlteradoEm()),
                antimicrobiano.getIsActive()
        );
    }


    public static MedicamentoResponse antimicrobianoToMedicamentoResponse(Antimicrobiano antimicrobiano) {
        return new MedicamentoResponse(
                antimicrobiano.getIdAntimicrobiano(),
                antimicrobiano.getDescricao(),
                antimicrobiano.getCodigo(),
                ObjectUtils.safeGet(antimicrobiano.getClasseAntimicrobiano(), ClasseAntimicrobiano::getIdClasseAntimicrobano),
                antimicrobiano.getIsActive()
        );
    }


    public static Antimicrobiano medicamentoRequestToAntimicrobiano(MedicamentoRequest medicamentoRequest) {
        var classeAntimicrobiano = ClasseAntimicrobiano.of(medicamentoRequest.idClasseAntimicrobiano());
        var antimicrobiano = new Antimicrobiano();

        antimicrobiano.setIdAntimicrobiano(medicamentoRequest.idMedicamento());
        antimicrobiano.setCodigo(medicamentoRequest.codigo());
        antimicrobiano.setDescricao(medicamentoRequest.descricao());
        antimicrobiano.setIsActive(medicamentoRequest.isActive());
        antimicrobiano.setClasseAntimicrobiano(classeAntimicrobiano);

        return antimicrobiano;
    }


}
