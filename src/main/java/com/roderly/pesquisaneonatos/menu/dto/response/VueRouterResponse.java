package com.roderly.pesquisaneonatos.menu.dto.response;

public record VueRouterResponse(
        String path,
        String name,
        String component,
        String title
) {
}
