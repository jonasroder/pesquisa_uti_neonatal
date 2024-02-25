package com.roderly.microbiomelabufu.paciente.dto.request;

import java.math.BigInteger;

public record FotoPerfilMetadataRequest(
        String name,
        Long size,
        String type
) {
}
