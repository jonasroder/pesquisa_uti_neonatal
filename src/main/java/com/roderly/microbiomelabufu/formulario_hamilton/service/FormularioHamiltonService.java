package com.roderly.microbiomelabufu.formulario_hamilton.service;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.consulta.repository.ConsultaRepository;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.request.FormularioHamiltonRequest;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.FormularioHamiltonPageResponse;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.FormularioHamiltonReponse;
import com.roderly.microbiomelabufu.paciente.dto.response.PacienteFormularioResponse;
import com.roderly.microbiomelabufu.formulario_hamilton.mapper.FormularioHamiltonMapper;
import com.roderly.microbiomelabufu.formulario_hamilton.repository.FormularioHamiltonRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FormularioHamiltonService {
    @Autowired
    FormularioHamiltonRepository formularioHamiltonRepository;
    @Autowired
    ConsultaRepository consultaRepository;

    @Transactional
    public ApiResponseDTO save(FormularioHamiltonRequest request) throws IOException {
        var formularioHamilton = FormularioHamiltonMapper.formularioHamiltonRequestToFormularioHamilton(request);
        var formularioHamiltonSalvo = formularioHamiltonRepository.save(formularioHamilton);
        return new ApiResponseDTO((long) formularioHamiltonSalvo.getId_formulario_hamilton(), "O Formulario de Hamilton foi Salvo!");
    }


    public FormularioHamiltonPageResponse load(Long id_consulta) {

        PacienteFormularioResponse infoHeaderPacienteConsulta = null;
        FormularioHamiltonReponse formularioResponse = null;

        if (id_consulta != 0) {
            var consulta = consultaRepository.findById(id_consulta).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada com ID: " + id_consulta));

            infoHeaderPacienteConsulta = FormularioHamiltonMapper.consultaToPacienteFormularioResponse(consulta);

            if (consulta.getFormularioHamilton() != null) {
                formularioResponse = FormularioHamiltonMapper.formularioHamiltonToFormularioHamiltonResponse(consulta.getFormularioHamilton());
            }
        }

        return new FormularioHamiltonPageResponse(infoHeaderPacienteConsulta, formularioResponse);
    }


}
