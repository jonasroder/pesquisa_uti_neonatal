package com.roderly.microbiomelabufu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/singin")
public class LoginController {

    @GetMapping("/login")
    public String hello() {
        return "meu primeiro código java 2";
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