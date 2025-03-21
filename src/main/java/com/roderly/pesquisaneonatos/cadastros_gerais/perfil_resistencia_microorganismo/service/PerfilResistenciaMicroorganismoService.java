package com.roderly.pesquisaneonatos.cadastros_gerais.perfil_resistencia_microorganismo.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.perfil_resistencia_microorganismo.mapper.PerfilResistenciaMicroorganismoMapper;
import com.roderly.pesquisaneonatos.cadastros_gerais.perfil_resistencia_microorganismo.repository.PerfilResistenciaMicrorganismoRepository;
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
public class PerfilResistenciaMicroorganismoService {

    private final PerfilResistenciaMicrorganismoRepository repository;


    public List<ListagemCadastroResponse> list() {
        var list = repository.findAll();

        return list.stream()
                .map(PerfilResistenciaMicroorganismoMapper::listar)
                .toList();
    }


    public CadastroResponse load(Long id) {
        var data = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado com ID: " + id));
        return PerfilResistenciaMicroorganismoMapper.response(data);
    }


    public ApiResponseDTO save(CadastroRequest request) throws IOException {
        var data = PerfilResistenciaMicroorganismoMapper.save(request);
        var save = repository.save(data);
        return new ApiResponseDTO(save.getIdPerfilResistenciaMicroorganismo(), "O registro foi Salvo!");
    }
}
