package com.roderly.microbiomelabufu.neonato.service;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.neonato.dto.request.NeonatoRequest;
import com.roderly.microbiomelabufu.neonato.dto.response.NeonatoListResponse;
import com.roderly.microbiomelabufu.neonato.dto.response.NeonatoResponse;
import com.roderly.microbiomelabufu.neonato.mapper.NeonatoMapper;
import com.roderly.microbiomelabufu.neonato.repository.NeonatoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NeonatoService {

    private final NeonatoRepository neonatoRepository;

    public ApiResponseDTO save (NeonatoRequest request) throws IOException {
        var prontuarioExistente = neonatoRepository.findByProntuario(request.prontuario());
        if (prontuarioExistente.isPresent() && request.idNeonato() == null) {
            return new ApiResponseDTO(null, "Número do prontuário já existente!");
        }

        var neonato = NeonatoMapper.convertNeonatoRequestToNeonato(request);
        var neonatoSalvo = neonatoRepository.save(neonato);

        return new ApiResponseDTO((long) neonatoSalvo.getIdNeonato(), "O registro foi Salvo!");
    }


    public NeonatoResponse load (Long id){
        var neonato = neonatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Neonato não encontrado com ID: " + id));
        return NeonatoMapper.convertNeonatoToNeonatoResponse(neonato);
    }


    public List<NeonatoListResponse> list (){
        var neonatoList = neonatoRepository.findAll();

        return neonatoList.stream()
                .map(NeonatoMapper::convertNeonatoToNeonatoListResponse)
                .toList();

    }


}
