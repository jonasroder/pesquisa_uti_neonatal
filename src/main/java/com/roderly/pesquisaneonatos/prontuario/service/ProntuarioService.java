package com.roderly.pesquisaneonatos.prontuario.service;

import com.roderly.pesquisaneonatos.common.dto.ApiResponseDTO;
import com.roderly.pesquisaneonatos.neonato.mapper.NeonatoMapper;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoRepository;
import com.roderly.pesquisaneonatos.prontuario.dto.request.EventoRequest;
import com.roderly.pesquisaneonatos.prontuario.dto.response.ColetaIsoladoResponse;
import com.roderly.pesquisaneonatos.prontuario.dto.response.ProntuarioResponse;
import com.roderly.pesquisaneonatos.prontuario.mapper.ProntuarioMapper;
import com.roderly.pesquisaneonatos.prontuario.repository.AntibiogramaIsoladoRepository;
import com.roderly.pesquisaneonatos.prontuario.repository.EventoEntidadeRepository;
import com.roderly.pesquisaneonatos.prontuario.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final EventoRepository eventoRepository;
    private final EventoEntidadeRepository eventoEntidadeRepository;
    private final NeonatoRepository neonatoRepository;
    private final AntibiogramaIsoladoRepository antibiogramaIsoladoRepository;

    public ProntuarioResponse load(Long id) {
        var neonato = neonatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Neonato não encontrado com ID: " + id));
        var neonatoResponse = NeonatoMapper.convertNeonatoToNeonatoListResponse(neonato);

        var eventos = eventoRepository.findByNeonatoIdNeonato(id);
        var eventosList = eventos.stream()
                .map(ProntuarioMapper::eventoToEventoResponse)
                .collect(Collectors.toSet());

        return new ProntuarioResponse(neonatoResponse, eventosList);
    }



    public ApiResponseDTO salvarEvento(EventoRequest request) throws IOException {
        var evento = ProntuarioMapper.eventoRequestToEvento(request);
        var eventoSalvo = eventoRepository.save(evento);

        if (request.idEntidade() != null) {
            var eventoEntidade = ProntuarioMapper.eventoAndEventoRequestToEventoEntidade(request, eventoSalvo);
            var eventoEntidadeSalvo = eventoEntidadeRepository.save(eventoEntidade);
        }

        return new ApiResponseDTO((long) eventoSalvo.getIdEvento(), "O registro foi Salvo!");
    }



    public List<ColetaIsoladoResponse> loadColetasProntuario(Long idNeonato) {
        // Busca inicial das coletas sem antibiogramas
        var coletasSemAntibiogramas = eventoRepository.findColetaIsoladoByIdNeonato(idNeonato);

        // Mapeia as coletas e preenche os antibiogramas usando o mapper
        return coletasSemAntibiogramas.stream()
                .map(coletaSemAntibiograma -> {
                    // Busca os antibiogramas para cada coleta de isolado
                    var antibiogramas = antibiogramaIsoladoRepository.findByIsoladoColetaId(coletaSemAntibiograma.id_isolado_coleta());

                    // Mapeia a coletaSemAntibiograma para ColetaIsoladoResponse com antibiogramas
                    return ProntuarioMapper.mapToColetaIsoladoResponse(coletaSemAntibiograma, antibiogramas);
                })
                .collect(Collectors.toList());
    }


}
