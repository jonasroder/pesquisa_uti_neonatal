package com.roderly.pesquisaneonatos.neonato.service;

import com.roderly.pesquisaneonatos.common.dto.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.excel.ExcelService;
import com.roderly.pesquisaneonatos.common.excel.ExcelSheetData;
import com.roderly.pesquisaneonatos.neonato.dto.request.NeonatoRequest;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoListResponse;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoResponse;
import com.roderly.pesquisaneonatos.neonato.excel.ExcelHelper;
import com.roderly.pesquisaneonatos.neonato.mapper.NeonatoMapper;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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



    public byte[] generateExcelReport() throws IOException {

        var neonatos = neonatoRepository.findAll();

        var neonatoList = neonatos.stream()
                .map(NeonatoMapper::convertNeonatoToNeonatoReportData)
                .toList();

        var mappings = ExcelHelper.createColumnMappings();

        List<ExcelSheetData<?>> sheetDataList = new ArrayList<>();
        sheetDataList.add(new ExcelSheetData<>("Neonatos", neonatoList, mappings));
        sheetDataList.add(new ExcelSheetData<>("Neonatos Teste 2", neonatoList, mappings));

        var excelFile = new ExcelService().generateExcelReport(sheetDataList);

        return excelFile;
    }


}
