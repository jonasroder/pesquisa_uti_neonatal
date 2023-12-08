package com.roderly.microbiomelabufu.login.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(@NotBlank String usuario, @NotBlank String senha) {
}
