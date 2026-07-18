package com.projetoIntegrador.Studia.service;

import com.projetoIntegrador.Studia.dto.EstudanteRequestDto;
import com.projetoIntegrador.Studia.exception.ErrodeDeValidacaoException;
import com.projetoIntegrador.Studia.exception.EstadoInvalidoException;
import com.projetoIntegrador.Studia.exception.RecursoDuplicadoException;
import com.projetoIntegrador.Studia.exception.RecursoNaoEncotradoException;
import com.projetoIntegrador.Studia.model.Estudante;
import com.projetoIntegrador.Studia.repository.CronogramaTarefaRepository;
import com.projetoIntegrador.Studia.repository.EstudanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EstudanteService {
    private final EstudanteRepository repository;
    private final CronogramaTarefaRepository cronogramaRepository;

    public EstudanteService(EstudanteRepository repository, CronogramaTarefaRepository cronogramaRepository) {
        this.repository = repository;
        this.cronogramaRepository = cronogramaRepository;
    }


    //======== Create =======
    public Estudante createEstudante(EstudanteRequestDto dados){

        if(dados.nome() == null || dados.nome().isEmpty()){
            throw new ErrodeDeValidacaoException("O nome deve ser preenchido.");
        }

        if(dados.email() == null || dados.email().isEmpty()){
            throw new ErrodeDeValidacaoException("O email deve ser preenchido.");
        }

        if(dados.senha() == null || dados.senha().isEmpty()){
            throw new ErrodeDeValidacaoException("A senha deve ser preenchida.");
        }

        if(repository.existsByUsername(dados.username())){
            throw new RecursoDuplicadoException("Usuario ja existente");
        };


        if(repository.existsByEmail(dados.email())){
            throw new RecursoDuplicadoException("Email ja cadastrado.");
        };

        Estudante novoEstudante = new Estudante();

        novoEstudante.setNome(dados.nome());
        novoEstudante.setUsername(dados.username());
        novoEstudante.setEmail(dados.email());
        novoEstudante.setSenha(dados.senha());

        novoEstudante.setAtivo(true);
        novoEstudante.setDataCriacao(LocalDate.now());

        return repository.save(novoEstudante);
    }

    //======= Read =======

    public List<Estudante> readAll(){
        return repository.findAll();
    }

    public Estudante readById(Long id){
        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncotradoException("Estudante não encotrado"));
    }
    // ====== update =====

    public Estudante update(Long id,EstudanteRequestDto estudanteAtualizado){
        Estudante estudante = readById(id);

        estudante.setNome(estudanteAtualizado.nome());
        estudante.setDescricaoPessoal(estudanteAtualizado.descricaoPessoal());

        if(estudanteAtualizado.senha() != null && !estudanteAtualizado.senha().isEmpty()) {
            estudante.setSenha(estudanteAtualizado.senha());
        }

        return repository.save(estudante);
    }


    public Estudante reactiveEstudante(Long id){

        Estudante estudante = readById(id);
        if(estudante.isAtivo()){
            throw new EstadoInvalidoException("Esta conta ja esta ativa");
        }
        estudante.setAtivo(true);
        return repository.save(estudante);
    }

    // ======= DELETE =======

    public void hardDelete(Long id){
        Estudante estudante = readById(id);
        repository.delete(estudante);
    }

    @Transactional
    public void softDelete(Long id){
        Estudante estudante = readById(id);
        estudante.setAtivo(false);
        repository.save(estudante);

        cronogramaRepository.deleteByEstudante(estudante);
    }

}
