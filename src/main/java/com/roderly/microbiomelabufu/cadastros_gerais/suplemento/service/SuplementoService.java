package com.roderly.microbiomelabufu.cadastros_gerais.suplemento.service;

import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.request.SuplementoIngredienteRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.request.SuplementoPageRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response.SuplementoIngredienteResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response.SuplementoListagemResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response.SuplementoPageResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.dto.response.SuplementoResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.mapper.SuplementoMapper;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.repository.SuplementoIngredienteRepository;
import com.roderly.microbiomelabufu.cadastros_gerais.suplemento.repository.SuplementoRepository;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SuplementoService {

    @Autowired
    SuplementoRepository suplementoRepository;
    @Autowired
    SuplementoIngredienteRepository suplementoIngredienteRepository;

    @Transactional
    public ApiResponseDTO saveSuplemento(SuplementoPageRequest request) throws IOException {
        var suplemento = SuplementoMapper.suplementoRequestToSuplemento(request.suplementoRequest());
        var suplementoSalvo = suplementoRepository.save(suplemento);

        for (SuplementoIngredienteRequest suplementoIngredienteRequest : request.suplementosIngredienteRequest()) {
            var suplementoIngrediente = SuplementoMapper.suplementoIgredienteRequestToSuplementoIgrediente(suplementoIngredienteRequest, suplementoSalvo);
            suplementoIngredienteRepository.save(suplementoIngrediente);
        }

        return new ApiResponseDTO((long) suplementoSalvo.getId_suplemento(), "O Suplemento foi Salvo!");
    }


    public SuplementoPageResponse getDadosSuplemento(Long id_suplemento) {

        SuplementoResponse suplementoResponse = null;
        List<SuplementoIngredienteResponse> suplementoIngredienteResponse = null;

        var suplemento = suplementoRepository.findById(id_suplemento).orElseThrow(() -> new EntityNotFoundException("Suplemento não encontrado com ID: " + id_suplemento));

        suplementoResponse = SuplementoMapper.suplementoTosuplementoResponse(suplemento);

        suplementoIngredienteResponse = suplemento.getSuplementoIngredientes().stream()
                .map(SuplementoMapper::suplementoIngredienteToSuplementoIngredienteResponse)
                .toList();

        return new SuplementoPageResponse(suplementoResponse, suplementoIngredienteResponse);
    }


    public List<SuplementoListagemResponse> getDadosListagemSuplementos(){
        var suplementos = suplementoRepository.findAll();

        return suplementos.stream()
                .map(SuplementoMapper::suplementoToSuplementoIngredienteListagem)
                .toList();
    }

}
