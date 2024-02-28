package com.roderly.microbiomelabufu.cadastros_gerais.medicamento.service;

import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.dto.request.MedicamentoListResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.dto.request.MedicamentoRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.dto.response.MedicamentoResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.mapper.MedicamentoMapper;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.repository.MedicamentoRepository;
import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MedicamentoService {
    @Autowired
    MedicamentoRepository medicamentoRepository;

    @Transactional
    public ApiResponseDTO saveMedicamento(MedicamentoRequest request) throws IOException{
        var medicamento = MedicamentoMapper.medicamentoRequestToMedicamento(request);
        var medicamentoSalvo = medicamentoRepository.save(medicamento);
        return new ApiResponseDTO((long) medicamentoSalvo.getId_medicamento(), "O Medicamento foi Salvo!");
    }


    public MedicamentoResponse getDadosMedicamento(Long id){
        var medicamento = medicamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado com ID: " + id));;
        var medicamentoResponse = MedicamentoMapper.medicamentoToMedicamentoResponse(medicamento);
        return medicamentoResponse;
    }


    public List<MedicamentoListResponse> getDadosListagemMedicamentos(){
        var medicamentos = medicamentoRepository.findAll();

        return medicamentos.stream()
                .map(MedicamentoMapper::medicamentoToMedicamentoListResponse)
                .toList();
    }

}
