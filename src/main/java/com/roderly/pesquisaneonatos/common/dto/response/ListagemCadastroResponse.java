package com.roderly.pesquisaneonatos.common.dto.response;

public record ListagemCadastroResponse(
        Long id,
        Long codigo,
        String descricao,
        String dataCadastro,
        String dataAlteracao,
        Boolean isActive
) {
}
