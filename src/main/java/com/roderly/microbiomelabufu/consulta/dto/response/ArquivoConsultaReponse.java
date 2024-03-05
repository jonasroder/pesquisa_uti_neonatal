package com.roderly.microbiomelabufu.consulta.dto.response;

public record ArquivoConsultaReponse(
        Long id_arquivo,
        String nome_arquivo,
        String data_upload,
        String tipo_arquivo,
        Boolean is_active
) {
}
