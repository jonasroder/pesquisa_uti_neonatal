package com.roderly.pesquisaneonatos.infra;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {

    // Catches SPA routes up to 3 levels deep, excluding known backend prefixes.
    // Negative lookahead prevents matching /ws, /api, /authenticate, /uploads, /error.
    @GetMapping(value = {
        "/{p:(?!(?:ws|api|authenticate|uploads|error)$)[^.]+}",
        "/{p1:(?!(?:ws|api|authenticate|uploads|error)$)[^.]+}/{p2:[^.]+}",
        "/{p1:(?!(?:ws|api|authenticate|uploads|error)$)[^.]+}/{p2:[^.]+}/{p3:[^.]+}"
    })
    public String spa() {
        return "forward:/index.html";
    }
}
