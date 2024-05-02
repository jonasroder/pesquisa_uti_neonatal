package com.roderly.microbiomelabufu.neonato.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.causa_obito.model.CausaObito;
import com.roderly.microbiomelabufu.cadastros_gerais.idade_gestacional.model.IdadeGestacional;
import com.roderly.microbiomelabufu.cadastros_gerais.local_nascimento.model.LocalNascimento;
import com.roderly.microbiomelabufu.cadastros_gerais.motivo_internacao.model.MotivoInternacao;
import com.roderly.microbiomelabufu.cadastros_gerais.peso_nascimento.model.PesoNascimento;
import com.roderly.microbiomelabufu.cadastros_gerais.rotura_membrana.model.RoturaMembrana;
import com.roderly.microbiomelabufu.cadastros_gerais.sexo.model.Sexo;
import com.roderly.microbiomelabufu.cadastros_gerais.sitio_cirurgia.model.SitioCirurgia;
import com.roderly.microbiomelabufu.cadastros_gerais.sitio_malformacao.model.SitioMalformacao;
import com.roderly.microbiomelabufu.cadastros_gerais.tipo_parto.model.TipoParto;
import com.roderly.microbiomelabufu.common.Utilitarios.DateUtil;
import com.roderly.microbiomelabufu.neonato.dto.request.NeonatoRequest;
import com.roderly.microbiomelabufu.neonato.dto.response.NeonatoListResponse;
import com.roderly.microbiomelabufu.neonato.dto.response.NeonatoResponse;
import com.roderly.microbiomelabufu.neonato.model.Neonato;

public class NeonatoMapper {

    public static Neonato convertNeonatoRequestToNeonato(NeonatoRequest request) {

        var sexo = request.idSexo() != null ? new Sexo(request.idSexo()) : null;
        var pesoNascimento = request.idPesoNascimento() != null ? new PesoNascimento(request.idPesoNascimento()) : null;
        var motivoInternacao = request.idMotivoInternacao() != null ? new MotivoInternacao(request.idMotivoInternacao()) : null;
        var localNascimento = request.idLocalNascimento() != null ? new LocalNascimento(request.idLocalNascimento()) : null;
        var idadeGestacional = request.idIdadeGestacional() != null ? new IdadeGestacional(request.idIdadeGestacional()) : null;
        var tipoParto = request.idTipoParto() != null ? new TipoParto(request.idTipoParto()) : null;
        var roturaMembrana =request.idRoturaMembrana() != null ? new RoturaMembrana(request.idRoturaMembrana()) : null;
        var sitioMalFormacao = request.idSitioMalformacao() != null ? new SitioMalformacao(request.idSitioMalformacao()) : null;
        var sitioCirurgia = request.idSitioCirurgia() != null ? new SitioCirurgia(request.idSitioCirurgia()) : null;
        var causaObito = request.idCausaObito() != null ? new CausaObito(request.idCausaObito()) : null;

        var neonato = new Neonato();
        neonato.setIdNeonato(request.idNeonato());
        neonato.setNomeMae(request.nomeMae());
        neonato.setProntuario(request.prontuario());
        neonato.setDataNascimento(request.dataNascimento());
        neonato.setDataInternacao(request.dataInternacao());
        neonato.setDataDesfecho(request.dataDesfecho());
        neonato.setObito(request.obito());
        neonato.setRiscoInfeccio(request.riscoInfeccioso());
        neonato.setSepseClinica(request.sepseClinica());
        neonato.setApgar1(request.apgar1());
        neonato.setApgar5(request.apgar5());
        neonato.setPesoGramas(request.pesoGramas());
        neonato.setSexo(sexo);
        neonato.setPesoNascimento(pesoNascimento);
        neonato.setMotivoInternacao(motivoInternacao);
        neonato.setLocalNascimento(localNascimento);
        neonato.setIdadeGestacional(idadeGestacional);
        neonato.setTipoParto(tipoParto);
        neonato.setRoturaMembrana(roturaMembrana);
        neonato.setSitioMalformacao(sitioMalFormacao);
        neonato.setSitioCirurgia(sitioCirurgia);
        neonato.setCausaObito(causaObito);

        return neonato;
    }


    public static NeonatoResponse convertNeonatoToNeonatoResponse(Neonato neonato) {
        return new NeonatoResponse(
                neonato.getIdNeonato(),
                neonato.getNomeMae(),
                neonato.getProntuario(),
                neonato.getDataNascimento(),
                neonato.getDataInternacao(),
                neonato.getDataDesfecho(),
                neonato.getObito(),
                neonato.getApgar1(),
                neonato.getApgar5(),
                neonato.getPesoGramas(),
                neonato.getSexo() != null ? neonato.getSexo().getIdSexo() : null,
                neonato.getPesoNascimento() != null ? neonato.getPesoNascimento().getIdPesoNascimento() : null,
                neonato.getMotivoInternacao() != null ? neonato.getMotivoInternacao().getIdMotivoInternacao() : null,
                neonato.getLocalNascimento() != null ? neonato.getLocalNascimento().getIdLocalNascimento() : null,
                neonato.getIdadeGestacional() != null ? neonato.getIdadeGestacional().getIdIdadeGestacional() : null,
                neonato.getTipoParto() != null ? neonato.getTipoParto().getIdTipoParto() : null,
                neonato.getRoturaMembrana() != null ? neonato.getRoturaMembrana().getIdRoturaMembrana() : null,
                neonato.getSitioMalformacao() != null ? neonato.getSitioMalformacao().getIdSitioMalformacao() : null,
                neonato.getSitioCirurgia() != null ? neonato.getSitioCirurgia().getIdSitioCirurgia() : null,
                neonato.getCausaObito() != null ? neonato.getCausaObito().getIdCausaObito() : null,
                neonato.getRiscoInfeccio(),
                neonato.getSepseClinica()
        );
    }



    public static NeonatoListResponse convertNeonatoToNeonatoListResponse(Neonato neonato) {

        var dataNascimento  = neonato.getDataNascimento() != null ? DateUtil.LocalDateToDateBR(neonato.getDataNascimento()) : null;
        var dataInternacao  = neonato.getDataInternacao() != null ? DateUtil.LocalDateToDateBR(neonato.getDataInternacao()) : null;
        var dataDesfecho    = neonato.getDataDesfecho() != null ? DateUtil.LocalDateToDateBR(neonato.getDataDesfecho()) : null;

        return new NeonatoListResponse(
                neonato.getIdNeonato(),
                neonato.getNomeMae(),
                neonato.getProntuario(),
                dataNascimento,
                dataInternacao,
                dataDesfecho
        );
    }
}
