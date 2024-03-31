package com.roderly.microbiomelabufu.agenda.mapper;

import com.roderly.microbiomelabufu.agenda.dto.request.AgendaRequest;
import com.roderly.microbiomelabufu.agenda.dto.response.AgendaResponse;
import com.roderly.microbiomelabufu.agenda.dto.response.UsuariosAgendaReponse;
import com.roderly.microbiomelabufu.agenda.model.Agenda;
import com.roderly.microbiomelabufu.agenda.model.StatusAgenda;
import com.roderly.microbiomelabufu.agenda.model.TipoEventoAgenda;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
import com.roderly.microbiomelabufu.usuario.model.Usuario;

public class AgendaMapper {

    public static Agenda agendaRequestToAgenda(AgendaRequest request){
        var agenda = new Agenda();
        var usuario = request.id_usuario() != null ? new Usuario(request.id_usuario()) : null;
        var statusAgenda = request.id_status_agenda() != null ? new StatusAgenda(request.id_status_agenda()) : null;
        var tipoEvento = request.id_tipo_evento() != null ? new TipoEventoAgenda(request.id_tipo_evento()) : null;
        var paciente = request.id_paciente() != null ? new Paciente(request.id_paciente()) : null;

        agenda.setId_evento(request.id_evento());
        agenda.setData_evento(request.data_evento());
        agenda.setHora_inicio(request.hora_inicio());
        agenda.setHora_fim(request.hora_fim());
        agenda.setDia_inteiro(request.dia_inteiro());
        agenda.setUrl(request.url());
        agenda.setDescricao(request.descricao());
        agenda.setIs_active(request.is_active());
        agenda.setUsuario(usuario);
        agenda.setStatus_agenda(statusAgenda);
        agenda.setTipo_evento_agenda(tipoEvento);
        agenda.setPaciente(paciente);

        return agenda;
    }


    public static UsuariosAgendaReponse usuariosToUsuariosAgendaRespopnse(Usuario usuario){

        String nome = usuario.getTitulo() != null ? usuario.getTitulo() + " " + usuario.getNome_completo() :  usuario.getNome_completo();

        return new UsuariosAgendaReponse(
                usuario.getId_usuario(),
                nome,
                usuario.getEspecialidade()
        );
    }



    public static AgendaResponse agendaToAgendaResponse(Agenda agenda){
        var id_status_agenda = agenda.getStatus_agenda() != null ? agenda.getStatus_agenda().getIdStatusAgenda() : null;
        var status_agenda_cor_associada = agenda.getStatus_agenda() != null ? agenda.getStatus_agenda().getCorAssociada() : null;
        var id_tipo_evento =  agenda.getTipo_evento_agenda() != null ? agenda.getTipo_evento_agenda().getId_tipo_evento_agenda() : null;
        var tipo_evento_cor_associada =  agenda.getTipo_evento_agenda() != null ? agenda.getTipo_evento_agenda().getCor_associada() : null;
        var tipo_evento =  agenda.getTipo_evento_agenda() != null ? agenda.getTipo_evento_agenda().getNome() : null;
        var id_paciente = agenda.getPaciente() != null ? agenda.getPaciente().getId_paciente() : null;
        var id_usuario = agenda.getUsuario() != null ? agenda.getUsuario().getId_usuario() : null;

        return new AgendaResponse(
                agenda.getId_evento(),
                agenda.getData_evento(),
                agenda.getHora_inicio(),
                agenda.getHora_fim(),
                agenda.getDia_inteiro(),
                agenda.getUrl(),
                agenda.getDescricao(),
                agenda.getIs_active(),
                id_usuario,
                id_status_agenda,
                status_agenda_cor_associada,
                id_tipo_evento,
                tipo_evento,
                tipo_evento_cor_associada,
                id_paciente
        );
    }
}
