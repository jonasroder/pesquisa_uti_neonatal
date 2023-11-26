package com.roderly.microbiomelabufu.infra;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteForwardingController {
    @RequestMapping(value = "/{path:[^.]*}")
    public String redirect() {
        return "forward:/";
    }
}
