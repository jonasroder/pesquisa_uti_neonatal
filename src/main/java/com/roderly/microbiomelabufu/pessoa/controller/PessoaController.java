package com.roderly.microbiomelabufu.pessoa.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @PostMapping("/insert")
    public String insert(@RequestBody Object dados) {

        return dados.toString();
    }


}
