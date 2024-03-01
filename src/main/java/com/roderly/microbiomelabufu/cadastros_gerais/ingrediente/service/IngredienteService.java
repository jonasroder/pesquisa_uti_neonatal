package com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.service;

import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.dto.request.IngredienteRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.dto.response.IngredienteResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.mapper.IngredienteMapper;
import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.repository.IngredienteRepository;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class IngredienteService {

    @Autowired
    IngredienteRepository ingredienteRepository;

    @Transactional
    public ApiResponseDTO save(IngredienteRequest request) throws IOException{
        var ingrediente = IngredienteMapper.ingredienteRequestToIngrediente(request);
        var ingredienteSalvo = ingredienteRepository.save(ingrediente);

        return new ApiResponseDTO((long) ingredienteSalvo.getId_ingrediente(), "O Ingrediente foi Salvo!");
    }


    public IngredienteResponse getDadosIngrediente(Long id_ingrediente){
        var ingrediente = ingredienteRepository.findById(id_ingrediente).orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado com ID: " + id_ingrediente));;
        var ingredienteResponse = IngredienteMapper.ingredienteToIngredienteResponse(ingrediente);

        return ingredienteResponse;
    }


    public List<IngredienteResponse> getDadosListagemIngredientes(){
        var ingredientes = ingredienteRepository.findAll();

        var listaIngredientes = ingredientes.stream()
                .map(IngredienteMapper::ingredienteToIngredienteResponse)
                .toList();

        return listaIngredientes;
    }
}
