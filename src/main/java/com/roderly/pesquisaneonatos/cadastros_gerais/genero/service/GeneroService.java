package com.roderly.pesquisaneonatos.cadastros_gerais.genero.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.genero.dto.request.GeneroRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.genero.dto.response.GeneroResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.genero.mapper.GeneroMapper;
import com.roderly.pesquisaneonatos.cadastros_gerais.genero.repository.GeneroRepository;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;


    public List<ListagemCadastroResponse> list() {
        var neonatoList = generoRepository.findAll();

        return neonatoList.stream()
                .map(GeneroMapper::generoToListagemCadastroResponse)
                .toList();
    }


    public GeneroResponse load(Long id) {
        var load = generoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Genero não encontrado com ID: " + id));
        return GeneroMapper.generoToGeneroResponse(load);
    }


    public ApiResponseDTO save(GeneroRequest request) throws IOException {
        var mapped = GeneroMapper.generoRequestToGenero(request);
        var saved = generoRepository.save(mapped);
        return new ApiResponseDTO(saved.getIdGenero(), "O registro foi Salvo!");
    }
}
