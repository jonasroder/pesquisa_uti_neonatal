package com.roderly.microbiomelabufu.paciente.dto.response;

public record PacienteCompletoResponse(
        PacienteResponse pacienteResponse,
        EnderecoResponse enderecoResponse,
        FotoPerfilResponse fotoPerfilResponse
) {
}
