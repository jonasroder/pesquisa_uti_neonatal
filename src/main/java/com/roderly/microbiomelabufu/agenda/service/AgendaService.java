package com.roderly.microbiomelabufu.agenda.service;

import com.roderly.microbiomelabufu.agenda.dto.request.AgendaRequest;
import com.roderly.microbiomelabufu.agenda.dto.response.UsuariosAgendaReponse;
import com.roderly.microbiomelabufu.agenda.mapper.AgendaMapper;
import com.roderly.microbiomelabufu.agenda.repository.AgendaRepository;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.usuario.repository.UsuarioRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final UsuarioRoleRepository usuarioRoleRepository;


    @Transactional
    public ApiResponseDTO save(AgendaRequest request) throws IOException {
        var agenda = AgendaMapper.agendaRequestToAgenda(request);
        var agendaSalvo = agendaRepository.save(agenda);

        return new ApiResponseDTO((long) agendaSalvo.getId_evento(), "O evento foi Salvo!");
    }


    @Transactional
    public List<UsuariosAgendaReponse> getUsuariosAgenda(){
        var medicoRoleIds = Set.of(1L, 2L, 3L, 4L);
        var usuarios = usuarioRoleRepository.findUsuariosByRoleIds(medicoRoleIds);

        return usuarios.stream()
                .map(AgendaMapper::usuariosToUsuariosAgendaRespopnse)
                .toList();
    }

}
