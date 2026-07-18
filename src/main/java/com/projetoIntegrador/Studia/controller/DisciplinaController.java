package com.projetoIntegrador.Studia.controller;

import com.projetoIntegrador.Studia.dto.DisciplinaRequestDto;
import com.projetoIntegrador.Studia.model.Disciplina;
import com.projetoIntegrador.Studia.service.DisciplinaService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/disciplinas") // Plural, seguindo a boa prática REST
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    // ===== CREATE =====
    @PostMapping
    public ResponseEntity<Disciplina> createDisciplina(@RequestBody DisciplinaRequestDto dto) {
        Disciplina novaDisciplina = disciplinaService.createDisciplina(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDisciplina);
    }

    // ===== READ =====
    @GetMapping
    public ResponseEntity<Page<Disciplina>> readAll(Pageable pageable) {
        Page<Disciplina> disciplinas = disciplinaService.listDisciplina(pageable);
        return ResponseEntity.ok(disciplinas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> readById(@PathVariable Long id) {
        Disciplina disciplina = disciplinaService.readById(id);
        return ResponseEntity.ok(disciplina);
    }

    // ===== UPDATE =====
    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Long id, @RequestBody DisciplinaRequestDto dto) {
        Disciplina disciplinaAtualizada = disciplinaService.update(id, dto);
        return ResponseEntity.ok(disciplinaAtualizada);
    }

    // ===== DELETE =====
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        disciplinaService.deleteDisciplina(id);
        return ResponseEntity.noContent().build(); // Retorna 204 (sem conteúdo), que é o padrão correto
    }
}