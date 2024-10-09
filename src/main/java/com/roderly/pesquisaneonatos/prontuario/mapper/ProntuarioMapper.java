package com.roderly.pesquisaneonatos.prontuario.mapper;

import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import com.roderly.pesquisaneonatos.prontuario.dto.request.EventoRequest;
import com.roderly.pesquisaneonatos.prontuario.dto.response.AntibiogramaIsoladoResponse;
import com.roderly.pesquisaneonatos.prontuario.dto.response.ColetaIsoladoResponse;
import com.roderly.pesquisaneonatos.prontuario.dto.response.ColetaIsoladoSemAntibiogramasResponse;
import com.roderly.pesquisaneonatos.prontuario.dto.response.EventoResponse;
import com.roderly.pesquisaneonatos.prontuario.model.AntibiogramaIsolado;
import com.roderly.pesquisaneonatos.prontuario.model.Evento;
import com.roderly.pesquisaneonatos.prontuario.model.EventoEntidade;
import com.roderly.pesquisaneonatos.prontuario.model.TipoEvento;

import java.util.List;

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
                evento.getIsActive()
        );
    }


    public static ColetaIsoladoResponse mapToColetaIsoladoResponse(
            ColetaIsoladoSemAntibiogramasResponse coletaSemAntibiogramas,
            List<AntibiogramaIsolado> antibiogramas
    ) {
        return new ColetaIsoladoResponse(
                coletaSemAntibiogramas.idEvento(),
                coletaSemAntibiogramas.idEventoEntidade(),
                coletaSemAntibiogramas.dataEvento(),
                coletaSemAntibiogramas.observacao(),
                coletaSemAntibiogramas.idSitioColeta(),
                coletaSemAntibiogramas.descricao(),
                coletaSemAntibiogramas.id_isolado_coleta(),
                coletaSemAntibiogramas.id_microorganismo(),
                coletaSemAntibiogramas.id_perfil_resistencia_microorganismo(),
                coletaSemAntibiogramas.id_mecanismo_resistencia_microorganismo(),
                antibiogramas // Preenchendo a lista de antibiogramas
        );
    }

}
