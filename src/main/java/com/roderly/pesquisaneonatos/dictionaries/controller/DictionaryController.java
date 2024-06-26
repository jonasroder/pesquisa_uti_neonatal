package com.roderly.pesquisaneonatos.dictionaries.controller;

import com.roderly.pesquisaneonatos.dictionaries.dto.AutocompleteRequestDTO;
import com.roderly.pesquisaneonatos.dictionaries.dto.ValueLabelDTO;
import com.roderly.pesquisaneonatos.dictionaries.service.DictionaryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping("/autocomplete")
    public ResponseEntity<List<ValueLabelDTO>> getAutocompleteData(@Valid @RequestBody AutocompleteRequestDTO autocompleteRequestDTO) {
        List<ValueLabelDTO> data = dictionaryService.findIdAndLabelByTableAndCondition(
                autocompleteRequestDTO.idColumn(),
                autocompleteRequestDTO.descColumn(),
                autocompleteRequestDTO.tableName(),
                autocompleteRequestDTO.whereClause(),
                autocompleteRequestDTO.is_active()
        );

        return ResponseEntity.ok(data);
    }

}
