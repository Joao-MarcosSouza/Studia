package com.projetoIntegrador.Studia.controller;

import com.projetoIntegrador.Studia.dto.TarefaEstudoRequestDto;
import com.projetoIntegrador.Studia.model.TarefaEstudo;
import com.projetoIntegrador.Studia.service.TarefaEstudoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaEstudoService tarefaService;

    public TarefaController(TarefaEstudoService tarefaService) {
        this.tarefaService = tarefaService;
    }


    //======= CREATE ======
    @PostMapping
    public ResponseEntity<TarefaEstudo> createTarefa(@RequestBody TarefaEstudoRequestDto tarefa) {
        TarefaEstudo criada = tarefaService.createTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    // ====== READ ======
    @GetMapping
    public ResponseEntity<Page<TarefaEstudo>> listAll(Pageable pageable) {
        return ResponseEntity.ok(tarefaService.listTarefa(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<TarefaEstudo> readById(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.readById(id));
    }

    //======= UPDATE ========
    @PutMapping("/{id}")
    public ResponseEntity<TarefaEstudo> atualizar(@PathVariable Long id, @RequestBody TarefaEstudoRequestDto tarefaAtualizada) {
        TarefaEstudo atualizada = tarefaService.update(id, tarefaAtualizada);
        return ResponseEntity.ok(atualizada);
    }

    // ======= DELETE =======
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deleteTarefa(id);
        return ResponseEntity.noContent().build();
    }
}