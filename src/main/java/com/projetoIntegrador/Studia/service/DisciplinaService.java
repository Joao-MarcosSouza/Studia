package com.projetoIntegrador.Studia.service;

import com.projetoIntegrador.Studia.dto.DisciplinaRequestDto;
import com.projetoIntegrador.Studia.exception.RecursoDuplicadoException;
import com.projetoIntegrador.Studia.exception.RecursoNaoEncotradoException;
import com.projetoIntegrador.Studia.model.Disciplina;
import com.projetoIntegrador.Studia.repository.DisciplinaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class DisciplinaService {
    private final DisciplinaRepository repository;


    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    //====== CREATE ======

    public Disciplina createDisciplina(DisciplinaRequestDto dados){
        if(repository.existsByTitulo(dados.titulo())){
            throw new RecursoDuplicadoException("Esse titulo ja existe.");
        }

        Disciplina novaDisciplina = new Disciplina();

        novaDisciplina.setDescricao(dados.descricao());
        novaDisciplina.setTitulo(dados.titulo());
        novaDisciplina.setAreaDeConhecimento(dados.areaDeConhecimento());

        return repository.save(novaDisciplina);
    }

    //========= READ =========

    public Page<Disciplina> listDisciplina(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Disciplina readById(Long id){
        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncotradoException("Disciplina não encotrado."));
    }

    //====== UPDATE ======

    public Disciplina update(Long id, DisciplinaRequestDto disciplinaAtualizada){
        Disciplina disciplina = readById(id);

        disciplina.setTitulo(disciplinaAtualizada.titulo());
        disciplina.setAreaDeConhecimento(disciplinaAtualizada.areaDeConhecimento());
        disciplina.setDescricao(disciplinaAtualizada.descricao());

        return repository.save(disciplina);
    }

    //====== DELETE ======

    public void deleteDisciplina(Long id){
        Disciplina disciplina = readById(id);
        repository.delete(disciplina);
    }
}
