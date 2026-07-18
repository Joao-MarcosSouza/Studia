package com.projetoIntegrador.Studia.repository;

import com.projetoIntegrador.Studia.model.Cronograma;
import com.projetoIntegrador.Studia.model.Estudante;
import com.projetoIntegrador.Studia.model.TarefaEstudo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CronogramaTarefaRepository extends JpaRepository<Cronograma,Long> {
    boolean existsByEstudanteAndTarefaEstudo(Estudante estudante, TarefaEstudo tarefa);
    void deleteByEstudante(Estudante estudante);
    List<Cronograma> findByEstudanteId(Long estudanteId);
}
