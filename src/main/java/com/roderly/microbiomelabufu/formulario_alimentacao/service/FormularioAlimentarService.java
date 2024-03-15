package com.roderly.microbiomelabufu.formulario_alimentacao.service;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.consulta.repository.ConsultaRepository;
import com.roderly.microbiomelabufu.formulario_alimentacao.dto.request.FormularioAlimentarRequest;
import com.roderly.microbiomelabufu.formulario_alimentacao.dto.response.FormularioAlimentarPageResponse;
import com.roderly.microbiomelabufu.formulario_alimentacao.dto.response.FormularioAlimentarResponse;
import com.roderly.microbiomelabufu.formulario_alimentacao.mapper.FormularioAlimentarMapper;
import com.roderly.microbiomelabufu.formulario_alimentacao.repository.FormularioAlimentarRepository;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.request.FormularioHamiltonRequest;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.FormularioHamiltonPageResponse;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.FormularioHamiltonReponse;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.PacienteFormularioResponse;
import com.roderly.microbiomelabufu.formulario_hamilton.mapper.FormularioHamiltonMapper;
import com.roderly.microbiomelabufu.formulario_hamilton.repository.FormularioHamiltonRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FormularioAlimentarService {
    @Autowired
    FormularioAlimentarRepository formularioAlimentarRepository;
    @Autowired
    ConsultaRepository consultaRepository;

    @Transactional
    public ApiResponseDTO save(FormularioAlimentarRequest request) throws IOException {
        var formulario = FormularioAlimentarMapper.formularioAlimentarRequestToFormularioAlimentar(request);
        var formularioSalvo = formularioAlimentarRepository.save(formulario);
        return new ApiResponseDTO((long) formularioSalvo.getId_formulario_alimentar(), "O Formulario Alimentar foi Salvo!");
    }


    public FormularioAlimentarPageResponse load(Long id_consulta) {

        PacienteFormularioResponse infoHeaderPacienteConsulta = null;
        FormularioAlimentarResponse formularioResponse = null;

        if (id_consulta != 0) {
            var consulta = consultaRepository.findById(id_consulta).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada com ID: " + id_consulta));

            infoHeaderPacienteConsulta = FormularioHamiltonMapper.consultaToPacienteFormularioResponse(consulta);

            if (consulta.getFormularioAlimentar() != null) {
                formularioResponse = FormularioAlimentarMapper.formularioAlimentarToFormularioAlimentarResponse(consulta.getFormularioAlimentar());
            }
        }

        return new FormularioAlimentarPageResponse(infoHeaderPacienteConsulta, formularioResponse);
    }


}
