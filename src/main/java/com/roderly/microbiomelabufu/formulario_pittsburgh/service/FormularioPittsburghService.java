package com.roderly.microbiomelabufu.formulario_pittsburgh.service;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.consulta.repository.ConsultaRepository;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.request.FormularioHamiltonRequest;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.FormularioHamiltonPageResponse;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.FormularioHamiltonReponse;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.PacienteFormularioResponse;
import com.roderly.microbiomelabufu.formulario_hamilton.mapper.FormularioHamiltonMapper;
import com.roderly.microbiomelabufu.formulario_pittsburgh.dto.request.FormularioPittsburghRequest;
import com.roderly.microbiomelabufu.formulario_pittsburgh.dto.response.FormularioPittsburghPageResponse;
import com.roderly.microbiomelabufu.formulario_pittsburgh.dto.response.FormularioPittsburghResponse;
import com.roderly.microbiomelabufu.formulario_pittsburgh.mapper.FormularioPittsburghMapper;
import com.roderly.microbiomelabufu.formulario_pittsburgh.repository.FormularioPittsburghRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FormularioPittsburghService {
    @Autowired
    FormularioPittsburghRepository formularioPittsburghRepository;
    @Autowired
    ConsultaRepository consultaRepository;

    @Transactional
    public ApiResponseDTO save(FormularioPittsburghRequest request) throws IOException {

        var formulario = FormularioPittsburghMapper.formularioPittsburghRequestToFormularioPittsburgh(request);
        var formularioSalvo  = formularioPittsburghRepository.save(formulario);

        return new ApiResponseDTO((long) formularioSalvo.getId_formulario_pittsburgh(), "O Formulario de Pittsburgh foi Salvo!");
    }



    public FormularioPittsburghPageResponse load(Long id_consulta) {

        PacienteFormularioResponse infoHeaderPacienteConsulta = null;
        FormularioPittsburghResponse formularioResponse = null;

        if (id_consulta != 0) {
            var consulta = consultaRepository.findById(id_consulta).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada com ID: " + id_consulta));

            infoHeaderPacienteConsulta = FormularioHamiltonMapper.consultaToPacienteFormularioResponse(consulta);

            if (consulta.getFormularioPittsburgh() != null) {
                formularioResponse = FormularioPittsburghMapper.formularioPittsburghToFormularioPittsburghResponse(consulta.getFormularioPittsburgh());
            }
        }

        return new FormularioPittsburghPageResponse(infoHeaderPacienteConsulta, formularioResponse);
    }

}
