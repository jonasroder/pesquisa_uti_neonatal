package com.roderly.pesquisaneonatos.neonato.excel;

import com.roderly.pesquisaneonatos.prontuario.model.Evento;

import java.util.List;

public class ProcedimentosEpisodioContext {
    public List<Evento> cirurgiasList;
    public List<Evento> flebotomiaList;
    public List<Evento> cvuList;
    public List<Evento> intracathList;
    public List<Evento> cvcDuploLumenList;
    public List<Evento> piccList;
    public List<Evento> entubacaoList;
    public List<Evento> sondaVesicalList;
    public List<Evento> nutricaoParenteralList;
    public List<Evento> drenoList;
    public List<Evento> medicacaoList;


    public ProcedimentosEpisodioContext(List<Evento> eventos) {

       this.cirurgiasList = filtrarListaEventosPorTipo(eventos, 9L);
       this.flebotomiaList = filtrarListaEventosPorTipo(eventos, 8L);
       this.cvuList = filtrarListaEventosPorTipo(eventos, 7L);
       this.intracathList = filtrarListaEventosPorTipo(eventos, 11L);
       this.cvcDuploLumenList = filtrarListaEventosPorTipo(eventos, 12L);
       this.piccList = filtrarListaEventosPorTipo(eventos, 6L);
       this.entubacaoList = filtrarListaEventosPorTipo(eventos, 3L);
       this.sondaVesicalList = filtrarListaEventosPorTipo(eventos, 4L);
       this.nutricaoParenteralList = filtrarListaEventosPorTipo(eventos, 5L);
       this.drenoList = filtrarListaEventosPorTipo(eventos, 2L);
       this.medicacaoList = filtrarListaEventosPorTipo(eventos, 1L);

    }


    public List<Evento> filtrarListaEventosPorTipo(List<Evento> eventos, Long tipo) {
        return eventos.stream()
                .filter(evento -> evento.getTipoEvento().getIdTipoEvento().equals(tipo))
                .toList();
    }
}
