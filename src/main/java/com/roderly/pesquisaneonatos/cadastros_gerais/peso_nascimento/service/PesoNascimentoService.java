package com.roderly.pesquisaneonatos.cadastros_gerais.peso_nascimento.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.peso_nascimento.mapper.PesoNascimentoMapper;
import com.roderly.pesquisaneonatos.cadastros_gerais.peso_nascimento.repository.PesoNascimentoRepository;
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
public class PesoNascimentoService {

    private final PesoNascimentoRepository repository;


    public List<ListagemCadastroResponse> list() {
        var list = repository.findAll();

        return list.stream()
                .map(PesoNascimentoMapper::listar)
                .toList();
    }


    public CadastroResponse load(Long id) {
        var data = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado com ID: " + id));
        return PesoNascimentoMapper.response(data);
    }


    public ApiResponseDTO save(CadastroRequest request) throws IOException {
        var data = PesoNascimentoMapper.save(request);
        var save = repository.save(data);
        return new ApiResponseDTO(save.getIdPesoNascimento(), "O registro foi Salvo!");
    }
}
