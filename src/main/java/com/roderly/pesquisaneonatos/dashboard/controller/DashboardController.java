package com.roderly.pesquisaneonatos.dashboard.controller;

import com.roderly.pesquisaneonatos.dashboard.dto.response.*;
import com.roderly.pesquisaneonatos.dashboard.service.DashboardService;
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
}
