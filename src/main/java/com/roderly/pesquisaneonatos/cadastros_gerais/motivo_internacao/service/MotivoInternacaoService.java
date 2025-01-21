package com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.dto.request.MotivoInternacaoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.dto.response.MotivoInternacaoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.mapper.MotivoInternacaoMapper;
import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.repository.MotivoInternacaoRepository;
import com.roderly.pesquisaneonatos.cadastros_gerais.sistema_fisiologico.repository.SistemaFisiologicoRepository;
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
public class MotivoInternacaoService {

    private final MotivoInternacaoRepository motivoInternacaoRepository;
    private final SistemaFisiologicoRepository sistemaFisiologicoRepository;


    public List<ListagemCadastroResponse> list() {
        var motivoInternacao = motivoInternacaoRepository.findAll();

        return motivoInternacao.stream()
                .map(MotivoInternacaoMapper::motivoInternacaoToListagemCadastroResponse)
                .toList();
    }


    public MotivoInternacaoResponse load(Long id) {
        var load = motivoInternacaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cadastro não encontrado com ID: " + id));
        return MotivoInternacaoMapper.motivoInternacaoToMotivoInternacaoResponse(load);
    }


    public ApiResponseDTO save(MotivoInternacaoRequest request) throws IOException {
        // Buscar a entidade SistemaFisiologico existente
        var sistemaFisiologico = sistemaFisiologicoRepository.findById(request.codigo())
                .orElseThrow(() -> new IllegalArgumentException("Sistema Fisiológico não encontrado para o código: " + request.codigo()));

        // Mapear para a entidade MotivoInternacao
        var motivoInternacao = MotivoInternacaoMapper.motivoInternacaoRequestToMotivoInternacao(request);
        motivoInternacao.setSistemaFisiologico(sistemaFisiologico);

        // Salvar no repositório
        var saved = motivoInternacaoRepository.save(motivoInternacao);

        return new ApiResponseDTO(saved.getIdMotivoInternacao(), "O registro foi Salvo!");
    }

}
