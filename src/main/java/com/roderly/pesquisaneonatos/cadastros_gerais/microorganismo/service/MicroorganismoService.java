package com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.dto.request.MicroorganismoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.dto.response.MicroorganismoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.mapper.MicroorganismoMapper;
import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.repository.MicroorganismoRepository;
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
public class MicroorganismoService {

    private final MicroorganismoRepository microorganismoRepository;


    public List<ListagemCadastroResponse> list() {
        var neonatoList = microorganismoRepository.findAll();

        return neonatoList.stream()
                .map(MicroorganismoMapper::microorganismoToListagemCadastroResponse)
                .toList();
    }


    public MicroorganismoResponse load(Long id) {
        var load = microorganismoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Microorganismo não encontrada com ID: " + id));
        return MicroorganismoMapper.microorganismoToMicroorganismoReponse(load);
    }


    public ApiResponseDTO save(MicroorganismoRequest request) throws IOException {
        var mapped = MicroorganismoMapper.MicroorganismoRequestToMicroorganismo(request);
        var saved = microorganismoRepository.save(mapped);
        return new ApiResponseDTO(saved.getIdMicroorganismo(), "O registro foi Salvo!");
    }
}
