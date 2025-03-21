package com.roderly.pesquisaneonatos.cadastros_gerais.causa_obito.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.causa_obito.mapper.CausaObitoMapper;
import com.roderly.pesquisaneonatos.cadastros_gerais.causa_obito.repository.CausaObitoRepository;
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
public class CausaObitoService {

    private final CausaObitoRepository repository;


    public List<ListagemCadastroResponse> list() {
        var list = repository.findAll();

        return list.stream()
                .map(CausaObitoMapper::listar)
                .toList();
    }


    public CadastroResponse load(Long id) {
        var data = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado com ID: " + id));
        return CausaObitoMapper.response(data);
    }


    public ApiResponseDTO save(CadastroRequest request) throws IOException {
        var data = CausaObitoMapper.save(request);
        var save = repository.save(data);
        return new ApiResponseDTO(save.getIdCausaObito(), "O registro foi Salvo!");
    }
}
