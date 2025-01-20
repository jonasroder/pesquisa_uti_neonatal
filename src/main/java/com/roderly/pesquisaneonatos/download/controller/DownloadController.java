package com.roderly.pesquisaneonatos.download.controller;

import com.roderly.pesquisaneonatos.common.dto.response.ApiResponseDTO;
import com.roderly.pesquisaneonatos.common.dto.response.ListagemCadastroResponse;
import com.roderly.pesquisaneonatos.download.dto.request.DownloadExcelNeonatoRequest;
import com.roderly.pesquisaneonatos.download.dto.response.DownloadExcelNeonatoResponse;
import com.roderly.pesquisaneonatos.download.service.DownloadService;
import com.roderly.pesquisaneonatos.menu.controller.MenuController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/download")
@RequiredArgsConstructor
public class DownloadController {

    private final DownloadService downloadService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponseDTO> save(@RequestBody DownloadExcelNeonatoRequest request) throws IOException {
        ApiResponseDTO response = downloadService.save(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/list")
    public ResponseEntity<List<DownloadExcelNeonatoResponse>> list() {
        var list = downloadService.list();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}/file")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) throws IOException {
        byte[] fileData = downloadService.getFileData(id);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=arquivo_" + id + ".xlsx")
                .body(fileData);
    }

}
