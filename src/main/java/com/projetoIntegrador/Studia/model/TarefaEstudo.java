package com.projetoIntegrador.Studia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class TarefaEstudo {

    private Long id;

    @ManyToOne

    private Long disciplinaId;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "nivel_dificuldade")
    private String nivelDeDificuldade;

    @Column(name = "duracao_estimada_minutos")
    private Integer duracaoEstimadaMinutos;
}
