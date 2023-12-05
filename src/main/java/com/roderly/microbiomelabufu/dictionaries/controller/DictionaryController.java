package com.roderly.microbiomelabufu.dictionaries.controller;

import com.roderly.microbiomelabufu.dictionaries.dto.AutocompleteRequestDTO;
import com.roderly.microbiomelabufu.dictionaries.dto.ValueLabelDTO;
import com.roderly.microbiomelabufu.dictionaries.service.DictionaryService;
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
    public ResponseEntity<List<ValueLabelDTO>> getAutocompleteData(@Valid @RequestBody AutocompleteRequestDTO requestDTO) {
        List<ValueLabelDTO> data = dictionaryService.findIdAndLabelByTableAndCondition(
                requestDTO.getIdColumn(),
                requestDTO.getDescColumn(),
                requestDTO.getTableName(),
                requestDTO.getWhereClause(),
                requestDTO.getSituacao()
        );

        return ResponseEntity.ok(data);
    }

}
