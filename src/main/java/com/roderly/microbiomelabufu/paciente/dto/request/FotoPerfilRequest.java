package com.roderly.microbiomelabufu.paciente.dto.request;

public record FotoPerfilRequest(
        String base64,
        FotoPerfilMetadataRequest metadata
) {
}
