package com.roderly.microbiomelabufu.formulario_hamilton.service;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.request.FormularioHamiltonRequest;
import com.roderly.microbiomelabufu.formulario_hamilton.dto.response.FormularioHamiltonReponse;
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

    @Transactional
    public ApiResponseDTO save(FormularioHamiltonRequest request) throws IOException {
        var formularioHamilton = FormularioHamiltonMapper.formularioHamiltonRequestToFormularioHamilton(request);
        var formularioHamiltonSalvo = formularioHamiltonRepository.save(formularioHamilton);
        return new ApiResponseDTO((long) formularioHamiltonSalvo.getId_formulario_hamilton(), "O Formulario de Hamilton foi Salvo!");
    }


    public FormularioHamiltonReponse load(Long id_consulta) {
        var formularioHamiltonOptional = formularioHamiltonRepository.findByConsulta_Id_Consulta(id_consulta);
        if (formularioHamiltonOptional.isPresent()) {
            var formularioHamilton = formularioHamiltonOptional.get();
            return FormularioHamiltonMapper.formularioHamiltonToFormularioHamiltonResponse(formularioHamilton);
        } else {
            throw new EntityNotFoundException("Formulário de Hamilton não encontrado para a consulta com ID: " + id_consulta);
        }
    }


}
