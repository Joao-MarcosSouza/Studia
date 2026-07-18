package com.projetoIntegrador.Studia.service;

import com.projetoIntegrador.Studia.dto.CronogramaRequestDto;
import com.projetoIntegrador.Studia.exception.EstadoInvalidoException;
import com.projetoIntegrador.Studia.exception.RecursoNaoEncontradoException;
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
                .orElseThrow(() -> new RecursoNaoEncontradoException("Estudante não encontrado."));

        if(!estudante.isAtivo()){
            throw new EstadoInvalidoException("Não é possivel criar um cronograma pra um estudante inativo");
        }

        TarefaEstudo tarefa = tarefaRepository.findById(dados.tarefaEstudoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tarefa não encontrada"));

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

    public List <Cronograma> readyByEstudanteId(Long estudanteId){
        List<Cronograma> tarefasDoEstudante = repository.findByEstudanteId(estudanteId);

        return tarefasDoEstudante;
    }
    // ===== UPDATE =====
    public Cronograma update(Long id, CronogramaRequestDto dto) {

        Cronograma cronogramaExistente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Agendamento não encontrado com o ID: " + id));

        if (dto.dataAgendada() != null) {
            cronogramaExistente.setDataAgendada(dto.dataAgendada());
        }

        if (dto.status() != null && !dto.status().isBlank()) {
            cronogramaExistente.setStatus(dto.status());
        }

        return repository.save(cronogramaExistente);
    }
    //===== DELETE =====

    public void deleteCronograma(Long id){
        Cronograma cronograma = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Agendamento não Encotrado com o ID" + id));
        repository.delete(cronograma);
    }
}