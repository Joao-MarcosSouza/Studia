package com.projetoIntegrador.Studia.service;

import com.projetoIntegrador.Studia.dto.CronogramaRequestDto;
import com.projetoIntegrador.Studia.model.Cronograma;
import com.projetoIntegrador.Studia.model.Estudante;
import com.projetoIntegrador.Studia.model.TarefaEstudo;
import com.projetoIntegrador.Studia.repository.CronogramaTarefaRepository;
import com.projetoIntegrador.Studia.repository.EstudanteRepository;
import com.projetoIntegrador.Studia.repository.TarefaEstudoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CronogramaService {
    private final CronogramaTarefaRepository repository;
    private final EstudanteRepository estudanteRepository;
    private final TarefaEstudoRepository tarefaRepository;

    public CronogramaService(CronogramaTarefaRepository repository, EstudanteRepository estudanteRepository,
                             TarefaEstudoRepository tarefaRepository) {
        this.repository = repository;
        this.estudanteRepository = estudanteRepository;
        this.tarefaRepository = tarefaRepository;
    }

    // ========= CREATE =========
    public Cronograma createCronograma(CronogramaRequestDto dados) {

        Estudante estudante = estudanteRepository.findById(dados.estudanteId())
                .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado."));

        if(!estudante.isAtivo()){
            throw new IllegalArgumentException("Não é possivel criar um cronograma pra um estudante inativo");
        }

        TarefaEstudo tarefa = tarefaRepository.findById(dados.tarefaEstudoId())
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        if(repository.existsByEstudanteAndTarefaEstudo(estudante,tarefa)){
            throw new IllegalArgumentException("Esta tarefa ja esta no cronograma do estudante.");
        }

        Cronograma novoCronograma = new Cronograma();
        novoCronograma.setStatus("PENDENTE");
        novoCronograma.setDataAgendada(dados.dataAgendada());

        novoCronograma.setEstudante(estudante);
        novoCronograma.setTarefaEstudo(tarefa);

        return repository.save(novoCronograma);
    }

    //===== READ =====

    public List<Cronograma> findAll(){
        return repository.findAll();
    }
}