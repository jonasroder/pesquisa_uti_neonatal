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


    public List<InfeccoesPorAgenteResponse> getColonizacoesPorAgente() {
        return neonatoRepository.buscarColonizacoesPorAgenteUltimos180Dias().stream()
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


    public Long getInternacoesEmAberto() {
        return neonatoRepository.countInternacoesEmAberto();
    }


    public List<UltimosNeonatosCadastradosResponse> getListaInternacoesEmAberto() {
        return neonatoRepository.findInternacoesEmAbertoList().stream()
                .map(DashboardMapper::mapInternacoesEmAberto)
                .toList();
    }


    public DashboardMetricasResponse getMetricas() {
        Long pacientesDia = neonatoRepository.getTotalPacientesDia180d();
        Long totalInfeccoes = neonatoRepository.countInfeccoesUltimos180d();
        double densidade = (pacientesDia != null && pacientesDia > 0)
                ? Math.round((totalInfeccoes * 1000.0 / pacientesDia) * 10.0) / 10.0
                : 0.0;

        List<Object[]> obitoStats = neonatoRepository.getObitoStats30d();
        Long totalObitos = 0L, totalDesfechos = 0L;
        double taxaMortalidade = 0.0;
        if (!obitoStats.isEmpty()) {
            Object[] row = obitoStats.get(0);
            totalObitos = row[0] != null ? ((Number) row[0]).longValue() : 0L;
            totalDesfechos = row[1] != null ? ((Number) row[1]).longValue() : 0L;
            taxaMortalidade = totalDesfechos > 0
                    ? Math.round((totalObitos * 100.0 / totalDesfechos) * 10.0) / 10.0
                    : 0.0;
        }

        Double tempoMedio = neonatoRepository.getTempoMedioInternacao90d();
        Double atrasoMedio = neonatoRepository.getAtrasoMedioCadastro();

        return new DashboardMetricasResponse(
                densidade,
                pacientesDia,
                totalObitos,
                totalDesfechos,
                taxaMortalidade,
                tempoMedio != null ? Math.round(tempoMedio * 10.0) / 10.0 : 0.0,
                atrasoMedio != null ? Math.round(atrasoMedio * 10.0) / 10.0 : 0.0
        );
    }


    public List<PerfilResistenciaDistribuicaoResponse> getRankingMicroorganismos() {
        return neonatoRepository.buscarRankingMicroorganismos().stream()
                .map(DashboardMapper::toPerfilResponse)
                .toList();
    }


    public List<PerfilResistenciaDistribuicaoResponse> getDistribuicaoPesoNascimento() {
        return neonatoRepository.buscarDistribuicaoPesoNascimento().stream()
                .map(DashboardMapper::toPerfilResponse)
                .toList();
    }


    public List<PacienteSemEventoResponse> getPacientesSemEventosRecentes() {
        return neonatoRepository.buscarPacientesSemEventosRecentes().stream()
                .map(DashboardMapper::toPacienteSemEventoResponse)
                .toList();
    }


}
