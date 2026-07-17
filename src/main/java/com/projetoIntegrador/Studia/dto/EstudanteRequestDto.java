package com.projetoIntegrador.Studia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstudanteRequestDto(String nome,String username, String email, String senha) {}
