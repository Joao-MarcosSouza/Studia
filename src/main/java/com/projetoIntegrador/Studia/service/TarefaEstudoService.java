package com.projetoIntegrador.Studia.service;

import com.projetoIntegrador.Studia.dto.TarefaEstudoRequestDto;
import com.projetoIntegrador.Studia.model.Disciplina;
import com.projetoIntegrador.Studia.model.TarefaEstudo;
import com.projetoIntegrador.Studia.repository.DisciplinaRepository;
import com.projetoIntegrador.Studia.repository.TarefaEstudoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TarefaEstudoService {
    private final TarefaEstudoRepository repository;
    private final DisciplinaRepository disciplinaRepository;

    public TarefaEstudoService(TarefaEstudoRepository repository, DisciplinaRepository disciplinaRepository) {
        this.repository = repository;
        this.disciplinaRepository = disciplinaRepository;
    }


    //====== CREATE ======

    public TarefaEstudo createTarefa(TarefaEstudoRequestDto dados){
        if(repository.existsByTitulo(dados.titulo())){
            throw new IllegalArgumentException("O titulo ja existe.");
        }

        Disciplina disciplinaEncontrada = disciplinaRepository.findById(dados.disciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada."));

        TarefaEstudo novaTarefa = new TarefaEstudo();

        novaTarefa.setTitulo(dados.titulo());
        novaTarefa.setDescricao(dados.descricao());
        novaTarefa.setNivelDeDificuldade(dados.nivelDificuldade());
        novaTarefa.setDuracaoEstimadaMinutos(dados.duracaoEmMinutos());

        novaTarefa.setDisciplina(disciplinaEncontrada);

        return  repository.save(novaTarefa);
    }
    //====== READ ======
    public Page<TarefaEstudo> listTarefa(Pageable pageable){
        return repository.findAll(pageable);
    }

    public TarefaEstudo readById(Long id){
        return repository.findById(id).orElseThrow(() -> new  IllegalArgumentException("Tarefa não encotrada."));
    }
    //====== UPDATE =====
    public TarefaEstudo update(Long id, TarefaEstudoRequestDto tarefaAtualizada){
        TarefaEstudo tarefa = readById(id);

        tarefa.setTitulo(tarefaAtualizada.titulo());
        tarefa.setDescricao(tarefaAtualizada.descricao());
        tarefa.setDuracaoEstimadaMinutos(tarefaAtualizada.duracaoEmMinutos());

        return repository.save(tarefa);
    }
    //====== DELETE =====

    public void deleteTarefa(Long id){
        TarefaEstudo tarefa = readById(id);
        repository.delete(tarefa);
    }
}
