package com.projetoIntegrador.Studia.service;

import com.projetoIntegrador.Studia.dto.DisciplinaRequestDto;
import com.projetoIntegrador.Studia.model.Disciplina;
import com.projetoIntegrador.Studia.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {
    private final DisciplinaRepository repository;


    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    //====== Create ======

    public Disciplina criarDisciplina(DisciplinaRequestDto dados){
        Disciplina novaDisciplina = new Disciplina();

        novaDisciplina.setDescricao(dados.descricao());
        novaDisciplina.setTitulo(dados.titulo());
        novaDisciplina.setAreaDeConhecimento(dados.areaDeConhecimento());

        return repository.save(novaDisciplina);
    }
}
