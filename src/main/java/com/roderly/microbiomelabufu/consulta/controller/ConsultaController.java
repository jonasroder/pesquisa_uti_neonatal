package com.roderly.microbiomelabufu.consulta.controller;

import com.roderly.microbiomelabufu.consulta.dto.response.ConsultaCompletaResponse;
import com.roderly.microbiomelabufu.consulta.mapper.ConsultaMapper;
import com.roderly.microbiomelabufu.consulta.repository.ConsultaRepository;
import com.roderly.microbiomelabufu.paciente.dto.response.PacienteCompletoResponse;
import com.roderly.microbiomelabufu.paciente.model.Paciente;
import com.roderly.microbiomelabufu.paciente.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ConsultaRepository consultaRepository;


    @GetMapping("/load/{id_paciente}/{id_consulta}")
    public ResponseEntity<ConsultaCompletaResponse> getDadosConsulta(@PathVariable Long id_paciente, Long id_consulta) {

        Tuple tuple = pacienteRepository.findPessoaWithImageProfile(id_paciente)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrada com ID: " + id_paciente));

        Paciente paciente = tuple.get(0, Paciente.class);
        String caminhoFotoPerfil = tuple.get(1, String.class);
        String planoDeSaude = tuple.get(2, String.class);




        ConsultaCompletaResponse response = ConsultaMapper.consultaToConsultaCompletoResponse(paciente, caminhoFotoPerfil, planoDeSaude);


        return ResponseEntity.ok(response);
    }
}
