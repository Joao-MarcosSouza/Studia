package com.projetoIntegrador.Studia.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class TarefaEstudo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn( name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "nivel_dificuldade")
    private String nivelDeDificuldade;

    @Column(name = "duracao_estimada_minutos")
    private Integer duracaoEstimadaMinutos;
}
