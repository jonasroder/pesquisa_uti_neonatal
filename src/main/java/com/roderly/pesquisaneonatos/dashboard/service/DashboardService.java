package com.roderly.pesquisaneonatos.dashboard.service;

import com.roderly.pesquisaneonatos.dashboard.dto.response.*;
import com.roderly.pesquisaneonatos.dashboard.mapper.DashboardMapper;
import com.roderly.pesquisaneonatos.neonato.repository.NeonatoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DashboardService {

    private final NeonatoRepository neonatoRepository;

    public List<UltimosNeonatosCadastradosResponse> getUltimosNeonatosCadastrados() {
        var neonatos = neonatoRepository.findInternadosNosUltimos7Dias();

        return neonatos.stream()
                .map(DashboardMapper::neonatoToUltimosNeonatosCadastradosResponse)
                .toList();
    }


    public List<CadastrosPorDiaDTOResponse> getCadastrosPorDia() {
        return neonatoRepository.buscarTotalInternacoesUltimos30Dias().stream()
                .map(DashboardMapper::mapToCadastrosPorDiaDTO)
                .toList();
    }


    public List<InfeccoesPorAgenteResponse> getInfeccoesPorAgente() {
        return neonatoRepository.buscarInfeccoesPorAgenteUltimos180Dias().stream()
                .map(DashboardMapper::mapToInfeccoesPorAgenteResponse)
                .toList();
    }

    public List<PerfilResistenciaDistribuicaoResponse> getDistribuicaoPerfis() {
        return neonatoRepository.buscarDistribuicaoPerfisResistencia180Dias().stream()
                .map(DashboardMapper::toPerfilResponse)
                .toList();
    }

    public List<MecanismoResistenciaDistribuicaoResponse> getDistribuicaoMecanismos() {
        return neonatoRepository.buscarDistribuicaoMecanismos180Dias().stream()
                .map(DashboardMapper::toMecanismoResponse)
                .toList();
    }

    public List<AntimicrobianoResistenciaResponse> getResistenciaPorAntimicrobiano() {
        return neonatoRepository.buscarResistenciaPorAntimicrobiano180Dias().stream()
                .map(DashboardMapper::toAntimicrobianoResponse)
                .toList();
    }


}
