package com.projetoIntegrador.Studia.service;

import com.projetoIntegrador.Studia.dto.TarefaEstudoRequestDto;
import com.projetoIntegrador.Studia.model.TarefaEstudo;
import com.projetoIntegrador.Studia.repository.TarefaEstudoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TarefaEstudoService {
    private final TarefaEstudoRepository repository;


    public TarefaEstudoService(TarefaEstudoRepository repository) {
        this.repository = repository;
    }

    //====== CREATE ======

    public TarefaEstudo createTarefa(TarefaEstudoRequestDto dados){
        if(repository.existsByTitulo(dados.titulo())){
            throw new IllegalArgumentException("O titulo ja existe.");
        }
        TarefaEstudo novaTarefa = new TarefaEstudo();

        novaTarefa.setTitulo(dados.titulo());
        novaTarefa.setDescricao(dados.descricao());

        novaTarefa.setNivelDeDificuldade(novaTarefa.getNivelDeDificuldade());
        novaTarefa.setDuracaoEstimadaMinutos(novaTarefa.getDuracaoEstimadaMinutos());

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
    //====== DELETE =====
}
