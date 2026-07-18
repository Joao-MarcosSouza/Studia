package com.projetoIntegrador.Studia.dto;

public record TarefaEstudoRequestDto(String titulo, String descricao, String nivelDificuldade,
                                     Integer duracaoEmMinutos, Long disciplinaId) {
}
