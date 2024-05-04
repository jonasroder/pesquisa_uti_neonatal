package com.roderly.pesquisaneonatos.neonato.controller;

import com.roderly.pesquisaneonatos.common.dto.ApiResponseDTO;
import com.roderly.pesquisaneonatos.neonato.dto.request.NeonatoRequest;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoListResponse;
import com.roderly.pesquisaneonatos.neonato.dto.response.NeonatoResponse;
import com.roderly.pesquisaneonatos.neonato.service.NeonatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/neonato")
@RequiredArgsConstructor
public class NeonatoController {

    private final NeonatoService neonatoService;


    @PostMapping("/save")
    public ResponseEntity<ApiResponseDTO> save(@RequestBody NeonatoRequest request) throws IOException {
        ApiResponseDTO response = neonatoService.save(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/load/{id}")
    public ResponseEntity<NeonatoResponse> load(@PathVariable Long id) {
        var response = neonatoService.load(id);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/list")
    public ResponseEntity<List<NeonatoListResponse>> list() {
        var list = neonatoService.list();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadExcel() throws IOException {
        byte[] excelFile = neonatoService.generateExcelReport();

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=neonatos.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(excelFile);
    }
}
