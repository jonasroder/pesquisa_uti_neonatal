package com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.service;

import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.dto.request.DiagonosticoRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.dto.response.DiagnosticoResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.mapper.DiagnosticoMapper;
import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.repository.DiagnosticoRepository;
import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.dto.request.IngredienteRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.dto.response.IngredienteResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.mapper.IngredienteMapper;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DiagnosticoService {
    @Autowired
    DiagnosticoRepository diagnosticoRepository;

    @Transactional
    public ApiResponseDTO save(DiagonosticoRequest request) throws IOException {
        var diagnostico = DiagnosticoMapper.diangnosticoRequestToDiagnostico(request);
        var diagnosticoSalvo = diagnosticoRepository.save(diagnostico);

        return new ApiResponseDTO((long) diagnosticoSalvo.getId_diagnostico(), "O Ingrediente foi Salvo!");
    }


    public DiagnosticoResponse loadById(Long id_diagnostico){
        var diagnostico = diagnosticoRepository.findById(id_diagnostico).orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado com ID: " + id_diagnostico));;
        var diagnosticoResponse = DiagnosticoMapper.diagnosticoToDiagnosticoReponse(diagnostico);

        return diagnosticoResponse;
    }


    public List<DiagnosticoResponse> getList(){
        var diagnosticos = diagnosticoRepository.findAll();

        var listaDiagnosticos = diagnosticos.stream()
                .map(DiagnosticoMapper::diagnosticoToDiagnosticoReponse)
                .toList();

        return listaDiagnosticos;
    }
}
