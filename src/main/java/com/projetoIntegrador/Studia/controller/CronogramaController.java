package com.projetoIntegrador.Studia.controller;

import com.projetoIntegrador.Studia.dto.CronogramaRequestDto;
import com.projetoIntegrador.Studia.model.Cronograma;
import com.projetoIntegrador.Studia.service.CronogramaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cronogramas") // Define a rota base
public class CronogramaController {

    private final CronogramaService cronogramaService;

    public CronogramaController(CronogramaService cronogramaService) {
        this.cronogramaService = cronogramaService;
    }


    // ====== CREATE ======
    @PostMapping
    public ResponseEntity<Cronograma> vincular(@RequestBody CronogramaRequestDto cronograma) {
        Cronograma criado = cronogramaService.createCronograma(cronograma);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    //====== READ =======
    @GetMapping
    public ResponseEntity<List<Cronograma>> listarTodos() {
        return ResponseEntity.ok(cronogramaService.findAll());
    }

    //
    @GetMapping("/estudante/{estudanteId}")
    public ResponseEntity<List<Cronograma>> listarPorEstudante(@PathVariable Long estudanteId) {
        return ResponseEntity.ok(cronogramaService.readyByEstudanteId(estudanteId));
    }

    // ====== DELETE =====
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTarefa(@PathVariable Long id) {
        cronogramaService.deleteCronograma(id);
        return ResponseEntity.noContent().build();
    }
}