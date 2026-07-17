package com.projetoIntegrador.Studia.service;

import com.projetoIntegrador.Studia.dto.EstudanteRequestDto;
import com.projetoIntegrador.Studia.dto.EstudanteUpdateDto;
import com.projetoIntegrador.Studia.model.Estudante;
import com.projetoIntegrador.Studia.repository.EstudanteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EstudanteService {
    private final EstudanteRepository repository;


    public EstudanteService(EstudanteRepository repository) {
        this.repository = repository;
    }

    //======== Create =======
    public Estudante create(EstudanteRequestDto dados){

        if(dados.nome() == null || dados.nome().isEmpty()){
            throw new IllegalArgumentException("O nome deve ser preenchido.");
        }

        if(dados.email() == null || dados.email().isEmpty()){
            throw new IllegalArgumentException("O email deve ser preenchido.");
        }

        if(dados.senha() == null || dados.senha().isEmpty()){
            throw new IllegalArgumentException("A senha deve ser preenchida.");
        }

        if(repository.existsByUsername(dados.username())){
            throw new IllegalArgumentException("Usuario ja existente");
        };


        if(repository.existsByEmail(dados.email())){
            throw new IllegalArgumentException("Email ja cadastrado.");
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
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Estudante não encotrado"));
    }
    // ====== update =====

    public Estudante update(Long id,EstudanteUpdateDto estudanteAtualizado){
        Estudante estudante = readById(id);

        estudante.setNome(estudanteAtualizado.nome());
        estudante.setDescricaoPessoal(estudanteAtualizado.descricaoPessoal());
        return repository.save(estudante);
    }


    public Estudante reactiveEstudante(Long id){

        Estudante estudante = readById(id);
        if(estudante.isAtivo()){
            throw new IllegalArgumentException("Esta conta ja esta ativa");
        }
        estudante.setAtivo(true);
        return repository.save(estudante);
    }

    // ======= DELETE =======

    public void hardDelete(Long id){
        Estudante estudante = readById(id);
        repository.delete(estudante);
    }

    public void softDelete(Long id){
        Estudante estudante = readById(id);
        estudante.setAtivo(false);
        repository.save(estudante);
    }

}
