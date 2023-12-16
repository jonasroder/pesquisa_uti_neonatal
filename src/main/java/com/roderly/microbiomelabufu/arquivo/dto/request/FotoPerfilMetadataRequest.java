package com.roderly.microbiomelabufu.arquivo.dto.request;

import java.math.BigInteger;

public record FotoPerfilMetadataRequest(
        String base64,
        String name,
        Long size,
        String type
) {
}
