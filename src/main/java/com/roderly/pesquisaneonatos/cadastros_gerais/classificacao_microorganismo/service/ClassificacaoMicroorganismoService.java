package com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.dto.request.ClassificacaoMicroorganismoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.dto.response.ClassificacaoMicroorganismoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.mapper.ClassificacaoMicroorganismoMapper;
import com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.repository.ClassificacaoMicroorganismoRepository;
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
public class ClassificacaoMicroorganismoService {

    private final ClassificacaoMicroorganismoRepository classificacaoMicroorganismoRepository;


    public List<ListagemCadastroResponse> list() {
        var neonatoList = classificacaoMicroorganismoRepository.findAll();

        return neonatoList.stream()
                .map(ClassificacaoMicroorganismoMapper::classificacaoMicroorganismoToListagemCadastroResponse)
                .toList();
    }


    public ClassificacaoMicroorganismoResponse load(Long id) {
        var load = classificacaoMicroorganismoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Classe não encontrada com ID: " + id));
        return ClassificacaoMicroorganismoMapper.classificacaoMicrorganismoToClassisficacaoMicroorganismoRepsponse(load);
    }


    public ApiResponseDTO save(ClassificacaoMicroorganismoRequest request) throws IOException {
        var mapped = ClassificacaoMicroorganismoMapper.classificacaoMicroorganismoRequestToClassificacaoMicroorganismo(request);
        var saved = classificacaoMicroorganismoRepository.save(mapped);
        return new ApiResponseDTO(saved.getIdClassificacaoMicroorganismo(), "O registro foi Salvo!");
    }
}
