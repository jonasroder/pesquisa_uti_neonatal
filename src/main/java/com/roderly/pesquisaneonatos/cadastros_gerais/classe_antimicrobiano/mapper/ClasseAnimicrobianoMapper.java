package com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.model.Antimicrobiano;
import com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.dto.request.ClasseAntimicrobianoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.dto.response.ClasseAntimicrobianoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.classe_antimicrobiano.model.ClasseAntimicrobiano;
import com.roderly.pesquisaneonatos.cadastros_gerais.tipo_antimicrobiano.model.TipoAntimicrobiano;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.Utilitarios.ObjectUtils;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class ClasseAnimicrobianoMapper {

    public static ListagemCadastroResponse classeAntimicrobianoToListagemCadastroResponse(ClasseAntimicrobiano classeAntimicrobiano) {
        return new ListagemCadastroResponse(
                classeAntimicrobiano.getIdClasseAntimicrobano(),
                classeAntimicrobiano.getCodigo(),
                classeAntimicrobiano.getDescricao(),
                DateUtil.LocalDateTimeToDateTimeBR(classeAntimicrobiano.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(classeAntimicrobiano.getAlteradoEm()),
                classeAntimicrobiano.getIsActive()
        );
    }


    public static ClasseAntimicrobianoResponse classeAntimicrobianoToClasseAnitimicrobianoResponse(ClasseAntimicrobiano classeAntimicrobiano) {
        return new ClasseAntimicrobianoResponse(
                classeAntimicrobiano.getIdClasseAntimicrobano(),
                classeAntimicrobiano.getDescricao(),
                classeAntimicrobiano.getCodigo(),
                ObjectUtils.safeGet(classeAntimicrobiano.getTipoAntimicrobiano(), TipoAntimicrobiano::getIdTipoAntimicrobiano),
                classeAntimicrobiano.getIsActive()
        );
    }


    public static ClasseAntimicrobiano classeAntimicrobianoRequestToClasseAntimicrobiano(ClasseAntimicrobianoRequest request) {
        var tipoAntimicrobiano =  new TipoAntimicrobiano(request.idTipoAntimicrobiano());
        var classeAntimicrobiano = new ClasseAntimicrobiano();

        classeAntimicrobiano.setIdClasseAntimicrobano(request.idClasseAntimicrobiano());
        classeAntimicrobiano.setCodigo(request.codigo());
        classeAntimicrobiano.setDescricao(request.descricao());
        classeAntimicrobiano.setIsActive(request.isActive());
        classeAntimicrobiano.setTipoAntimicrobiano(tipoAntimicrobiano);

        return classeAntimicrobiano;
    }


}
