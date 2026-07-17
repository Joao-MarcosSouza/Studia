package com.projetoIntegrador.Studia.model;

import com.projetoIntegrador.Studia.dto.EstudanteRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Estudante {
    public Estudante(EstudanteRequestDto dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
    }

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
