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
import com.roderly.pesquisaneonatos.prontuario.dto.projections.ClasseAntimicrobianoCountProjection;
import com.roderly.pesquisaneonatos.prontuario.dto.projections.EventoCountProjection;
import com.roderly.pesquisaneonatos.prontuario.dto.response.EventoTipoDiasResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NeonatoService {

    private final NeonatoRepository neonatoRepository;

    public ApiResponseDTO save(NeonatoRequest request) throws IOException {
        var prontuarioExistente = neonatoRepository.findByProntuario(request.prontuario());
        if (prontuarioExistente.isPresent() && request.idNeonato() == null) {
            return ApiResponseDTO.failure("Número do prontuário já existente!");
        }

        var neonato = NeonatoMapper.convertNeonatoRequestToNeonato(request);
        var neonatoSalvo = neonatoRepository.save(neonato);

        return new ApiResponseDTO(neonatoSalvo.getIdNeonato(), "O registro foi salvo!");

    }


    public NeonatoResponse load(Long id) {
        var neonato = neonatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Neonato não encontrado com ID: " + id));
        return NeonatoMapper.convertNeonatoToNeonatoResponse(neonato);
    }


    public List<NeonatoListResponse> list() {
        var neonatoList = neonatoRepository.findAll();

        return neonatoList.stream()
                .map(NeonatoMapper::convertNeonatoToNeonatoListResponse)
                .toList();

    }


    public byte[] generateExcelReport() throws IOException {

        var idsNeonatosControle = neonatoRepository.findIdsNeonatosControle();
        var neonatos = neonatoRepository.findAllById(idsNeonatosControle);

        var neonatosControle = neonatos.stream()
                .map(neonato -> {
                    var eventos = neonatoRepository.findEventoCountsByNeonato(neonato.getIdNeonato());
                    var classesAntimicrobianos = neonatoRepository.findClasseAntimicrobianoCountsByNeonato(neonato.getIdNeonato());
                    var diasUsoATB = neonatoRepository.getDiasUsoAnimicrobiano(neonato.getIdNeonato(), 1L);
                    var diasUsoATF = neonatoRepository.getDiasUsoAnimicrobiano(neonato.getIdNeonato(), 2L);

                    return NeonatoMapper.convertToNeonatoGrupoControleReportData(neonato, eventos, diasUsoATB, diasUsoATF, classesAntimicrobianos, this);
                })
                .collect(Collectors.toList());

        var mappings = ExcelHelper.createColumnMappings();

        List<ExcelSheetData<?>> sheetDataList = new ArrayList<>();
        sheetDataList.add(new ExcelSheetData<>("Grupo Controle", neonatosControle, mappings));
        sheetDataList.add(new ExcelSheetData<>("Grupo Infectado", neonatosControle, mappings));

        return new ExcelService().generateExcelReport(sheetDataList);
    }


    public EventoTipoDiasResponse getEventoTipoDias(List<EventoCountProjection> eventos, Long tipoEvento) {
        return eventos.stream()
                .filter(evento -> evento.getIdTipoEvento().equals(tipoEvento))
                .findFirst()
                .map(eventoCountProjection -> new EventoTipoDiasResponse(
                        1L,
                        eventoCountProjection.getNEventos()
                ))
                .orElseGet(() -> new EventoTipoDiasResponse(0L, 0L));
    }


    public EventoTipoDiasResponse getClasseAnimicrobianoDias(List<ClasseAntimicrobianoCountProjection> antimicrobianos, Long idClasseAntimicrobiano){
        return antimicrobianos.stream()
                .filter(evento -> evento.getIdClasseAntimicrobiano().equals(idClasseAntimicrobiano))
                .findFirst()
                .map(eventoCountProjection -> new EventoTipoDiasResponse(
                        1L,
                        eventoCountProjection.getNEventos()
                ))
                .orElseGet(() -> new EventoTipoDiasResponse(0L, 0L));
    }


}
