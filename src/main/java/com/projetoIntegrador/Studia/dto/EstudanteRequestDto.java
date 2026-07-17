package com.projetoIntegrador.Studia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstudanteRequestDto(
        @NotBlank
        String nome,
        @Email
        String email,
        @Size(min = 10, message = "A senha deve ter pelo menos 10 caracteres")
        String senha) {}
