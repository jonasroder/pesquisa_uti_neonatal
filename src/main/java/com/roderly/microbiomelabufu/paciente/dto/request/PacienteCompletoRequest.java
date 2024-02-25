package com.roderly.microbiomelabufu.paciente.dto.request;

public record PacienteCompletoRequest(
        PacienteRequest pacienteRequest,
        EnderecoRequest enderecoRequest,
        FotoPerfilRequest fotoPerfilRequest
) {}
