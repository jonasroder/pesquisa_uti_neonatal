package com.roderly.pesquisaneonatos.cadastros_gerais.idade_gestacional.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.idade_gestacional.mapper.IdadeGestacionalMapper;
import com.roderly.pesquisaneonatos.cadastros_gerais.idade_gestacional.repository.IdadeGestacionalRepository;
import com.roderly.pesquisaneonatos.common.dto.request.CadastroRequest;
import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.dto.response.CadastroResponse;
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
public class IdadeGestacionalService {

    private final IdadeGestacionalRepository repository;


    public List<ListagemCadastroResponse> list() {
        var list = repository.findAll();

        return list.stream()
                .map(IdadeGestacionalMapper::listar)
                .toList();
    }


    public CadastroResponse load(Long id) {
        var data = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado com ID: " + id));
        return IdadeGestacionalMapper.response(data);
    }


    public ApiResponseDTO save(CadastroRequest request) throws IOException {
        var data = IdadeGestacionalMapper.save(request);
        var save = repository.save(data);
        return new ApiResponseDTO(save.getIdIdadeGestacional(), "O registro foi Salvo!");
    }
}
