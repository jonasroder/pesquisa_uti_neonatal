package com.roderly.microbiomelabufu.consulta.controller;

import com.roderly.microbiomelabufu.common.dto.ApiResponseDTO;
import com.roderly.microbiomelabufu.consulta.dto.request.*;
import com.roderly.microbiomelabufu.consulta.dto.response.ConsultaCompletaResponse;
import com.roderly.microbiomelabufu.consulta.service.ConsultaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/load/{id_paciente}/{id_consulta}")
    public ResponseEntity<ConsultaCompletaResponse> getDadosConsulta(@PathVariable Long id_paciente, @PathVariable Long id_consulta) {
        ConsultaCompletaResponse response = consultaService.getDadosConsulta(id_paciente, id_consulta);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/save")
    public ResponseEntity<ApiResponseDTO> save(@RequestBody ConsultaCompletoRequest request) throws IOException {
        ApiResponseDTO response = consultaService.saveConsulta(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/upload")
    public void upload(
            @RequestParam("id_consulta") List<Long> idConsultas,
            @RequestParam("id_tipo_arquivo") List<Long> idTiposArquivo,
            @RequestParam("multipartFile") List<MultipartFile> files) throws IOException {

        consultaService.upload(idConsultas, idTiposArquivo, files);
    }


    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) throws IOException {
        return consultaService.downloadFile(fileId);
    }
}
