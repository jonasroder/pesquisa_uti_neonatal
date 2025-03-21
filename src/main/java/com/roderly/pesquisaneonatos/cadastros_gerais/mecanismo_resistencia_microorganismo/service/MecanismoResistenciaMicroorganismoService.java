package com.roderly.pesquisaneonatos.cadastros_gerais.mecanismo_resistencia_microorganismo.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.mecanismo_resistencia_microorganismo.mapper.MecanismoResistenciaMicroorganismoMapper;
import com.roderly.pesquisaneonatos.cadastros_gerais.mecanismo_resistencia_microorganismo.repository.MecanismoResistenciaMicrorganismoRepository;
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
public class MecanismoResistenciaMicroorganismoService {

    private final MecanismoResistenciaMicrorganismoRepository repository;


    public List<ListagemCadastroResponse> list() {
        var list = repository.findAll();

        return list.stream()
                .map(MecanismoResistenciaMicroorganismoMapper::listar)
                .toList();
    }


    public CadastroResponse load(Long id) {
        var data = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro não encontrado com ID: " + id));
        return MecanismoResistenciaMicroorganismoMapper.response(data);
    }


    public ApiResponseDTO save(CadastroRequest request) throws IOException {
        var data = MecanismoResistenciaMicroorganismoMapper.save(request);
        var save = repository.save(data);
        return new ApiResponseDTO(save.getIdMecanismoResistenciaMicroorganismo(), "O registro foi Salvo!");
    }
}
