package com.roderly.pesquisaneonatos.dictionaries.controller;

import com.roderly.pesquisaneonatos.dictionaries.dto.AutocompleteRequestDTO;
import com.roderly.pesquisaneonatos.dictionaries.dto.TabelaCodigoDTO;
import com.roderly.pesquisaneonatos.dictionaries.dto.ValueLabelDTO;
import com.roderly.pesquisaneonatos.dictionaries.service.DictionaryService;
import com.roderly.pesquisaneonatos.usuario.dto.response.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping("/autocomplete")
    public ResponseEntity<List<ValueLabelDTO>> getAutocompleteData(
            @Valid @RequestBody AutocompleteRequestDTO autocompleteRequestDTO) {

        List<ValueLabelDTO> data = dictionaryService.findIdAndLabelByTableAndCondition(
                autocompleteRequestDTO.idColumn(),
                autocompleteRequestDTO.descColumn(),
                autocompleteRequestDTO.tableName(),
                autocompleteRequestDTO.whereClause(),
                autocompleteRequestDTO.is_active(),
                autocompleteRequestDTO.additionalColumns()
        );

        return ResponseEntity.ok(data);
    }




    @PostMapping("/verificarCodigo")
    private ResponseEntity<TabelaCodigoDTO> buscarCodigoValido(@RequestBody TabelaCodigoDTO tabelaCodigoDTO) {
        var response = dictionaryService.buscarCodigoValido(tabelaCodigoDTO);
        return ResponseEntity.ok(response);
    }

}
