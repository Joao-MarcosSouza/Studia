package com.projetoIntegrador.Studia.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class CronogramaTarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudante_id", nullable = false)
    private Estudante estudante;

    @ManyToOne
    @JoinColumn(name = "tarefa_estudo_id", nullable = false)
    private TarefaEstudo tarefaEstudo;

    @Column(name = "data_agendamento")
    private LocalDateTime dataAgendamento;

    @Column(nullable = false)
    private String status;

}
