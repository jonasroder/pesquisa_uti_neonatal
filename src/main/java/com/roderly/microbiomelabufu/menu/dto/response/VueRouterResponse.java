package com.roderly.microbiomelabufu.menu.dto.response;

public record VueRouterResponse(
        String path,
        String name,
        String component,
        String title
) {
}
