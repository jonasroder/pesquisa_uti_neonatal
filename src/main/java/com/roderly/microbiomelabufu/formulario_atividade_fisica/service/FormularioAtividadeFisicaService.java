package com.roderly.microbiomelabufu.formulario_atividade_fisica.service;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.consulta.repository.ConsultaRepository;
import com.roderly.microbiomelabufu.formulario_atividade_fisica.dto.request.FormularioAtividadeFisicaRequest;
import com.roderly.microbiomelabufu.formulario_atividade_fisica.dto.response.FormularioAtividadeFisicaPageResponse;
import com.roderly.microbiomelabufu.formulario_atividade_fisica.dto.response.FormularioAtividadeFisicaResponse;
import com.roderly.microbiomelabufu.formulario_atividade_fisica.mapper.FormularioAtividadeFisicaMapper;
import com.roderly.microbiomelabufu.formulario_atividade_fisica.repository.FormularioAtividadeFisicaRepository;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.PacienteFormularioResponse;
import com.roderly.microbiomelabufu.formulario_hamilton.mapper.FormularioHamiltonMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FormularioAtividadeFisicaService {
    @Autowired
    FormularioAtividadeFisicaRepository formularioAtividadeFisicaRepository;

    @Autowired
    ConsultaRepository consultaRepository;

    @Transactional
    public ApiResponseDTO save(FormularioAtividadeFisicaRequest request) throws IOException {

        var formulario = FormularioAtividadeFisicaMapper.formularioAtividadeFisicaRequestToFormularioAtividadeFisica(request);
        var formularioSalvo  = formularioAtividadeFisicaRepository.save(formulario);

        return new ApiResponseDTO((long) formularioSalvo.getId_formulario_atividade_fisica(), "O Formulario de Atividade Física foi Salvo!");
    }



    public FormularioAtividadeFisicaPageResponse load(Long id_consulta) {

        PacienteFormularioResponse infoHeaderPacienteConsulta = null;
        FormularioAtividadeFisicaResponse formularioResponse = null;

        if (id_consulta != 0) {
            var consulta = consultaRepository.findById(id_consulta).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada com ID: " + id_consulta));

            infoHeaderPacienteConsulta = FormularioHamiltonMapper.consultaToPacienteFormularioResponse(consulta);

            if (consulta.getFormularioAtividadeFisica() != null) {
                formularioResponse = FormularioAtividadeFisicaMapper.formularioAtividadeFisicaToformularioAtividadeFisicaResponse(consulta.getFormularioAtividadeFisica());
            }
        }

        return new FormularioAtividadeFisicaPageResponse(infoHeaderPacienteConsulta, formularioResponse);
    }

}
