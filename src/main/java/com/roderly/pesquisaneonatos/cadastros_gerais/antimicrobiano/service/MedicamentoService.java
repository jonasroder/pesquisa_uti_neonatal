package com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.service;

import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.dto.request.MedicamentoRequest;
import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.dto.response.MedicamentoResponse;
import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.mapper.MedicamentoMapper;
import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.repository.AntimicrobianoRepository;
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
public class MedicamentoService {

    private final AntimicrobianoRepository antimicrobianoRepository;


    public List<ListagemCadastroResponse> list() {
        var list = antimicrobianoRepository.findAll();

        return list.stream()
                .map(MedicamentoMapper::antimicrobianoToListagemCadastroResponse)
                .toList();
    }


    public MedicamentoResponse load(Long id) {
        var medicamento = antimicrobianoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado com ID: " + id));
        return MedicamentoMapper.antimicrobianoToMedicamentoResponse(medicamento);
    }


    public ApiResponseDTO save(MedicamentoRequest request) throws IOException {
        var medicamento = MedicamentoMapper.medicamentoRequestToAntimicrobiano(request);
        var medicamentoSalvo = antimicrobianoRepository.save(medicamento);
        return new ApiResponseDTO(medicamentoSalvo.getIdAntimicrobiano(), "O registro foi Salvo!");
    }
}
