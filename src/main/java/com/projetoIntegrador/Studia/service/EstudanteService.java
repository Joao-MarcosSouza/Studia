package com.projetoIntegrador.Studia.service;

import com.projetoIntegrador.Studia.dto.EstudanteRequestDto;
import com.projetoIntegrador.Studia.model.Estudante;
import com.projetoIntegrador.Studia.repository.EstudanteRepository;
import org.springframework.stereotype.Service;

@Service
public class EstudanteService {
    private final EstudanteRepository repository;


    public EstudanteService(EstudanteRepository repository) {
        this.repository = repository;
    }

    public Estudante criar(EstudanteRequestDto dados){
        Estudante novoEstudante = new Estudante(dados);
        return repository.save(novoEstudante);

    }
}
