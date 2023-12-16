package com.roderly.microbiomelabufu.arquivo.dto.request;

public record FotoPerfilRequest(
        String base64,
        FotoPerfilMetadataRequest metadata
) {
}
