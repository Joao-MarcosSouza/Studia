package com.projetoIntegrador.Studia.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Estudante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false,length = 100)
    private String nome;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(name = "descricao_pessoal", columnDefinition = "TEXT")
    private String descricaoPessoal;

    @Column(nullable = false)
    private boolean ativo;

    @Column(name = "foto_perfil_url")
    private String fotoPerfil;

    @Column(name = "data_criacao", updatable = false)
    private LocalDate dataCriacao;
}
