package com.roderly.microbiomelabufu.controller;

import com.roderly.microbiomelabufu.model.UsuarioModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authenticate")
public class LoginController {

    @PostMapping("/user") // Adicione este método para lidar com solicitações POST
    public String authenticateUser(@RequestBody UsuarioModel data) {
        // Aqui você pode processar os dados de login
        // Certifique-se de criar uma classe LoginData para representar os dados de login
        return "Autenticação bem-sucedida"; // Substitua com a lógica real de autenticação
    }

    @GetMapping("/login2")
    public String hello2() {
        return "aprendendo essa coisa";
    }


    @GetMapping("/login/{id}")
    public String hello3(@PathVariable int id) {
        return "aprendendo essa coisa " + id;
    }

}