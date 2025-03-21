package com.roderly.pesquisaneonatos.cadastros_gerais.tipo_parto.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.tipo_parto.mapper.TipoPartoMapper;
import com.roderly.pesquisaneonatos.cadastros_gerais.tipo_parto.repository.TipoPartoRepository;
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
public class TipoPartoService {

    private final TipoPartoRepository repository;


    public List<ListagemCadastroResponse> list() {
        var list = repository.findAll();

        return list.stream()
                .map(TipoPartoMapper::listar)
                .toList();
    }


    public CadastroResponse load(Long id) {
        var data = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado com ID: " + id));
        return TipoPartoMapper.response(data);
    }


    public ApiResponseDTO save(CadastroRequest request) throws IOException {
        var data = TipoPartoMapper.save(request);
        var save = repository.save(data);
        return new ApiResponseDTO(save.getIdTipoParto(), "O registro foi Salvo!");
    }
}
