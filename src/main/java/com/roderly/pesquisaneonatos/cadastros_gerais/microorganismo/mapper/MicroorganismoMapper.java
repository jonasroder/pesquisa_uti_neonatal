package com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.model.ClassificacaoMicroorganismo;
import com.roderly.pesquisaneonatos.cadastros_gerais.genero.model.Genero;
import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.dto.request.MicroorganismoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.dto.response.MicroorganismoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.model.Microorganismo;
import com.roderly.pesquisaneonatos.common.Utilitarios.DateUtil;
import com.roderly.pesquisaneonatos.common.Utilitarios.ObjectUtils;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;

public class MicroorganismoMapper {

    public static ListagemCadastroResponse microorganismoToListagemCadastroResponse(Microorganismo microorganismo) {
        return new ListagemCadastroResponse(
                microorganismo.getIdMicroorganismo(),
                microorganismo.getCodigo(),
                microorganismo.getAcronimo(),
                DateUtil.LocalDateTimeToDateTimeBR(microorganismo.getCriadoEm()),
                DateUtil.LocalDateTimeToDateTimeBR(microorganismo.getAlteradoEm()),
                microorganismo.getIsActive()
        );
    }


    public static MicroorganismoResponse microorganismoToMicroorganismoReponse(Microorganismo microorganismo) {
        return new MicroorganismoResponse(
                microorganismo.getIdMicroorganismo(),
                ObjectUtils.safeGet(microorganismo.getGenero(), Genero::getIdGenero),
                ObjectUtils.safeGet(microorganismo.getClassificacaoMicroorganismo(), ClassificacaoMicroorganismo::getIdClassificacaoMicroorganismo),
                microorganismo.getEspecie(),
                microorganismo.getAcronimo(),
                microorganismo.getCodigo(),
                microorganismo.getIsActive()
        );
    }


    public static Microorganismo MicroorganismoRequestToMicroorganismo(MicroorganismoRequest request) {
        var genero = new Genero(request.idGenero());
        var classificacaoMicroorganismo = new ClassificacaoMicroorganismo(request.idClassificacaoMicroorganismo());

        var microorganismo = new Microorganismo();
        microorganismo.setIdMicroorganismo(request.idMicroorganismo());
        microorganismo.setCodigo(request.codigo());
        microorganismo.setAcronimo(request.acronimo());
        microorganismo.setEspecie(request.especie());
        microorganismo.setIsActive(request.isActive());
        microorganismo.setGenero(genero);
        microorganismo.setClassificacaoMicroorganismo(classificacaoMicroorganismo);

        return microorganismo;
    }


}
