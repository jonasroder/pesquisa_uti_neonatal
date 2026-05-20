package com.roderly.pesquisaneonatos.dashboard.controller;

import com.roderly.pesquisaneonatos.dashboard.dto.response.*;
import com.roderly.pesquisaneonatos.dashboard.service.DashboardService;
import com.roderly.pesquisaneonatos.dashboard.dto.response.DashboardMetricasResponse;
import com.roderly.pesquisaneonatos.dashboard.dto.response.PacienteSemEventoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;



    @GetMapping("/getUltimosNeonatosCadastrados")
    public ResponseEntity<List<UltimosNeonatosCadastradosResponse>> list() {
        var list = dashboardService.getUltimosNeonatosCadastrados();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/getCadastrosPorDia")
    public ResponseEntity<List<CadastrosPorDiaDTOResponse>> getCadastrosPorDia() {
        var lista = dashboardService.getCadastrosPorDia();
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/getInfeccoesPorAgente")
    public ResponseEntity<List<InfeccoesPorAgenteResponse>> getInfeccoesPorAgente() {
        var list = dashboardService.getInfeccoesPorAgente();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/getColonizacoesPorAgente")
    public ResponseEntity<List<InfeccoesPorAgenteResponse>> getColonizacoesPorAgente() {
        var list = dashboardService.getColonizacoesPorAgente();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/perfisResistencia")
    public List<PerfilResistenciaDistribuicaoResponse> getPerfis() {
        return dashboardService.getDistribuicaoPerfis();
    }


    @GetMapping("/mecanismosResistencia")
    public List<MecanismoResistenciaDistribuicaoResponse> getMecanismos() {
        return dashboardService.getDistribuicaoMecanismos();
    }


    @GetMapping("/resistenciaAntimicrobiano")
    public List<AntimicrobianoResistenciaResponse> getAntimicrobianos() {
        return dashboardService.getResistenciaPorAntimicrobiano();
    }


    @GetMapping("/internacoesEmAberto")
    public ResponseEntity<Long> getInternacoesEmAberto() {
        return ResponseEntity.ok(dashboardService.getInternacoesEmAberto());
    }


    @GetMapping("/listaInternacoesEmAberto")
    public ResponseEntity<List<UltimosNeonatosCadastradosResponse>> getListaInternacoesEmAberto() {
        return ResponseEntity.ok(dashboardService.getListaInternacoesEmAberto());
    }


    @GetMapping("/metricas")
    public ResponseEntity<DashboardMetricasResponse> getMetricas() {
        return ResponseEntity.ok(dashboardService.getMetricas());
    }


    @GetMapping("/rankingMicroorganismos")
    public ResponseEntity<List<PerfilResistenciaDistribuicaoResponse>> getRankingMicroorganismos() {
        return ResponseEntity.ok(dashboardService.getRankingMicroorganismos());
    }


    @GetMapping("/distribuicaoPeso")
    public ResponseEntity<List<PerfilResistenciaDistribuicaoResponse>> getDistribuicaoPeso() {
        return ResponseEntity.ok(dashboardService.getDistribuicaoPesoNascimento());
    }


    @GetMapping("/pacientesSemEventos")
    public ResponseEntity<List<PacienteSemEventoResponse>> getPacientesSemEventos() {
        return ResponseEntity.ok(dashboardService.getPacientesSemEventosRecentes());
    }
}
