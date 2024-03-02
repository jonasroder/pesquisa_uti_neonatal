package com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.service;

import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.dto.request.DiagonosticoRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.dto.response.DiagnosticoResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.diagnostico.mapper.DiagnosticoMapper;
import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.dto.request.TipoInformacaoSaudeRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.dto.resopnse.TipoInformacaoSaudeResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.mapper.TipoInformacaoSaudeMapper;
import com.roderly.microbiomelabufu.cadastros_gerais.tipo_informacao_saude.repository.TipoInformacaoSaudeRepository;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TipoInformacaoSaudeService {
    @Autowired
    TipoInformacaoSaudeRepository tipoInformacaoSaudeRepository;


    @Transactional
    public ApiResponseDTO save(TipoInformacaoSaudeRequest request) throws IOException {
        var informacaoSaude = TipoInformacaoSaudeMapper.tipoInformacaoSaudeRequestToTipoInformacaoSaude(request);
        var informacaoSaudeSalva = tipoInformacaoSaudeRepository.save(informacaoSaude);

        return new ApiResponseDTO((long) informacaoSaudeSalva.getId_tipo_informacao_saude(), "O tipo de informação saúde foi Salvo!");
    }


    public TipoInformacaoSaudeResponse loadById(Long id){
        var informacaoSaude = tipoInformacaoSaudeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tipo de informação saúde não encontrado com ID: " + id));;
        var informacaoSaudeResponse = TipoInformacaoSaudeMapper.tipoInformacaoSaudeTotipoInformacaoSaudeResponse(informacaoSaude);

        return informacaoSaudeResponse;
    }


    public List<TipoInformacaoSaudeResponse> getList(){
        var informacaoSaude = tipoInformacaoSaudeRepository.findAll();

        var listaTipoInformacaoSaudeMapper = informacaoSaude.stream()
                .map(TipoInformacaoSaudeMapper::tipoInformacaoSaudeTotipoInformacaoSaudeResponse)
                .toList();

        return listaTipoInformacaoSaudeMapper;
    }
}
