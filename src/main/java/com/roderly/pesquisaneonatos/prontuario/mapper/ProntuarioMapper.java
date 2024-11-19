package com.roderly.pesquisaneonatos.prontuario.mapper;

import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.model.Antimicrobiano;
import com.roderly.pesquisaneonatos.cadastros_gerais.mecanismo_resistencia_microorganismo.model.MecanismoResistenciaMicroorganismo;
import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.model.Microorganismo;
import com.roderly.pesquisaneonatos.cadastros_gerais.perfil_resistencia_microorganismo.model.PerfilResistenciaMicroorganismo;
import com.roderly.pesquisaneonatos.cadastros_gerais.resistencia_microorganismo.model.ResistenciaMicroorganismo;
import com.roderly.pesquisaneonatos.cadastros_gerais.via_administracao.model.ViaAdministracao;
import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import com.roderly.pesquisaneonatos.prontuario.dto.request.AntibiogramaIsoladoRequest;
import com.roderly.pesquisaneonatos.prontuario.dto.request.ColetaIsoladoRequest;
import com.roderly.pesquisaneonatos.prontuario.dto.request.EventoRequest;
import com.roderly.pesquisaneonatos.prontuario.dto.response.AntibiogramaIsoladoResponse;
import com.roderly.pesquisaneonatos.prontuario.dto.response.ColetaIsoladoResponse;
import com.roderly.pesquisaneonatos.prontuario.dto.response.ColetaIsoladoSemAntibiogramasResponse;
import com.roderly.pesquisaneonatos.prontuario.dto.response.EventoResponse;
import com.roderly.pesquisaneonatos.prontuario.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class ProntuarioMapper {

    public static Evento eventoRequestToEvento(EventoRequest request) {
        var neonato = request.idNeonato() != null ? new Neonato(request.idNeonato()) : null;
        var tipoEvento = request.idTipoEvento() != null ? new TipoEvento(request.idTipoEvento()) : null;

        var evento = new Evento();
        evento.setIdEvento(request.idEvento());
        evento.setTipoEvento(tipoEvento);
        evento.setNeonato(neonato);
        evento.setDataEvento(request.dataEvento());
        evento.setDiaInteiro(request.diaInteiro());
        evento.setObservacao(request.observacao());

        return evento;
    }


    public static EventoEntidade eventoAndEventoRequestToEventoEntidade(EventoRequest request, Evento evento) {
        var eventoEntidade = new EventoEntidade();
        eventoEntidade.setIdEventoEntidade(request.idEventoEntidade());
        eventoEntidade.setEvento(evento);
        eventoEntidade.setTipoEntidade(request.tipoEntidade());
        eventoEntidade.setIdEntidade(request.idEntidade());

        return eventoEntidade;
    }


    public static EventoResponse eventoToEventoResponse(Evento evento) {
        var idNeonato = evento.getNeonato() != null ? evento.getNeonato().getIdNeonato() : null;
        var idTipoEvento = evento.getTipoEvento() != null ? evento.getTipoEvento().getIdTipoEvento() : null;
        var tipoEvento = evento.getTipoEvento() != null ? evento.getTipoEvento().getDescricao() : null;
        var corEvento = evento.getTipoEvento() != null ? evento.getTipoEvento().getCorAssociada() : null;
        var idEventoEntidade = evento.getEventoEntidade() != null ? evento.getEventoEntidade().getIdEventoEntidade() : null;
        var tipoEntidade = evento.getEventoEntidade() != null ? evento.getEventoEntidade().getTipoEntidade() : null;
        var getIdEntidade = evento.getEventoEntidade() != null ? evento.getEventoEntidade().getIdEntidade() : null;
        var idEventoViaAdministracao = evento.getEventoViaAdministracao() != null ? evento.getEventoViaAdministracao().getIdEventoViaAdministracao() : null;
        var idViaAdministracao = evento.getEventoViaAdministracao() != null ? evento.getEventoViaAdministracao().getViaAdministracao().getIdViaAdministracao() : null;

        return new EventoResponse(
                evento.getIdEvento(),
                idNeonato,
                evento.getDataEvento(),
                idTipoEvento,
                tipoEvento,
                corEvento,
                idEventoEntidade,
                tipoEntidade,
                getIdEntidade,
                evento.getDiaInteiro(),
                evento.getObservacao(),
                evento.getIsActive(),
                idEventoViaAdministracao,
                idViaAdministracao
        );
    }


    public static ColetaIsoladoResponse mapToColetaIsoladoResponse(
            ColetaIsoladoSemAntibiogramasResponse coletaSemAntibiogramas,
            List<AntibiogramaIsolado> antibiogramas) {

        // Mapeia a lista de AntibiogramaIsolado para AntibiogramaIsoladoResponse
        List<AntibiogramaIsoladoResponse> antibiogramaIsolado = antibiogramas
                .stream()
                .map(ProntuarioMapper::antibiogramaIsoladoToAntibiogramaIsoladoResponse)
                .collect(Collectors.toList());

        var idPerfilResistenciaMicroorganismo = coletaSemAntibiogramas.idPerfilResistenciaMicroorganismo() != null ? coletaSemAntibiogramas.idPerfilResistenciaMicroorganismo() : 1L;
        var idMecanismoResistenciaMicroorganismo = coletaSemAntibiogramas.idMecanismoResistenciaMicroorganismo() != null ? coletaSemAntibiogramas.idMecanismoResistenciaMicroorganismo() : 1L;

        return new ColetaIsoladoResponse(
                coletaSemAntibiogramas.idEvento(),
                coletaSemAntibiogramas.idEventoEntidade(),
                coletaSemAntibiogramas.dataEvento(),
                coletaSemAntibiogramas.observacao(),
                coletaSemAntibiogramas.idSitioColeta(),
                coletaSemAntibiogramas.descricao(),
                coletaSemAntibiogramas.idIsoladoColeta(),
                coletaSemAntibiogramas.idMicroorganismo(),
                idPerfilResistenciaMicroorganismo,
                idMecanismoResistenciaMicroorganismo,
                coletaSemAntibiogramas.desconsiderarColeta(),
                antibiogramaIsolado
        );
    }


    public static AntibiogramaIsoladoResponse antibiogramaIsoladoToAntibiogramaIsoladoResponse(AntibiogramaIsolado antibiogramaIsolado) {

        var idIsoladoColeta = antibiogramaIsolado.getIsoladoColeta() != null ? antibiogramaIsolado.getIsoladoColeta().getIdIsoladoColeta() : null;
        var idAntimicrobiano = antibiogramaIsolado.getAntimicrobiano() != null ? antibiogramaIsolado.getAntimicrobiano().getIdAntimicrobiano() : null;
        var idResistenciaMicroorganismo = antibiogramaIsolado.getResistenciaMicroorganismo() != null ? antibiogramaIsolado.getResistenciaMicroorganismo().getIdResistenciaMicroorganismo() : null;

        return new AntibiogramaIsoladoResponse(
                antibiogramaIsolado.getIdAntibiogramaIsolado(),
                idIsoladoColeta,
                idAntimicrobiano,
                idResistenciaMicroorganismo,
                antibiogramaIsolado.getIsActive()
        );
    }


    public static IsoladoColeta coletaIsoladoRequestToIsoladoColeta(ColetaIsoladoRequest request) {

        var idEvento = new Evento(request.idEvento());
        var idMicroorganismo = new Microorganismo(request.idMicroorganismo());
        var idPerfilResistenciaMicroorganismo = new PerfilResistenciaMicroorganismo(request.idPerfilResistenciaMicroorganismo());
        var idMecanismoResistenciaMicroorganismo = new MecanismoResistenciaMicroorganismo(request.idMecanismoResistenciaMicroorganismo());

        var isoladoColeta = new IsoladoColeta();
        isoladoColeta.setIdIsoladoColeta(request.idIsoladoColeta());
        isoladoColeta.setEvento(idEvento);
        isoladoColeta.setMicroorganismo(idMicroorganismo);
        isoladoColeta.setPerfilResistenciaMicroorganismo(idPerfilResistenciaMicroorganismo);
        isoladoColeta.setMecanismoResistenciaMicroorganismo(idMecanismoResistenciaMicroorganismo);
        isoladoColeta.setDesconsiderarColeta(false);

        return isoladoColeta;
    }


    public static AntibiogramaIsolado antibiogramaIsoladoRequestToAntibiograma(AntibiogramaIsoladoRequest request, IsoladoColeta isoladoColeta) {

        var antimicrobiano = request.idAntimicrobiano() != null ? new Antimicrobiano(request.idAntimicrobiano()) : null;
        var resistenciaMicroorganismo = request.idResistenciaMicroorganismo() != null ? new ResistenciaMicroorganismo(request.idResistenciaMicroorganismo()) : null;

        var antibiogramaIsolado = new AntibiogramaIsolado();
        antibiogramaIsolado.setIdAntibiogramaIsolado(request.idAntibiogramaIsolado());
        antibiogramaIsolado.setIsoladoColeta(isoladoColeta);
        antibiogramaIsolado.setAntimicrobiano(antimicrobiano);
        antibiogramaIsolado.setResistenciaMicroorganismo(resistenciaMicroorganismo);

        return antibiogramaIsolado;
    }


    public static EventoViaAdministracao eventoToeventoViaAdministracao(Evento evento, Long idEventoViaAdministracao, Long idViaAdministracao) {

        var viaAdministracao = new ViaAdministracao(idViaAdministracao);
        var eventoViaAdministracao = new EventoViaAdministracao();

        eventoViaAdministracao.setIdEventoViaAdministracao(idEventoViaAdministracao);
        eventoViaAdministracao.setEvento(evento);
        eventoViaAdministracao.setViaAdministracao(viaAdministracao);

        return  eventoViaAdministracao;
    }
}
