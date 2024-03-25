package com.roderly.microbiomelabufu.agenda.mapper;

import com.roderly.microbiomelabufu.agenda.dto.request.AgendaRequest;
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
        var tipoEvento = request.id_tipo_evento_agenda() != null ? new TipoEventoAgenda(request.id_tipo_evento_agenda()) : null;
        var paciente = request.id_paciente() != null ? new Paciente(request.id_paciente()) : null;

        agenda.setId_evento(request.id_evento());
        agenda.setInicio(request.inicio());
        agenda.setFim(request.fim());
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
}
