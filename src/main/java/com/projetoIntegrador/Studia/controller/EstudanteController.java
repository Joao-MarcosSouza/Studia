package com.projetoIntegrador.Studia.controller;

import com.projetoIntegrador.Studia.dto.EstudanteRequestDto;
import com.projetoIntegrador.Studia.model.Estudante;
import com.projetoIntegrador.Studia.service.EstudanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {

    private final EstudanteService estudanteService;

    public EstudanteController(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }


    //===== CREATE ======
    @PostMapping
    public ResponseEntity<Estudante> createEstudante(@RequestBody EstudanteRequestDto estudante){
        Estudante estudanteSalvo = estudanteService.createEstudante(estudante);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudanteSalvo);
    }
    //===== READ =====
    @GetMapping("/{id}")
    public ResponseEntity<Estudante> readById(@PathVariable Long id){
        Estudante estudante = estudanteService.readById(id);
        return ResponseEntity.ok(estudante);
    }

    @GetMapping
    public ResponseEntity<List<Estudante>> readAll(){
        List<Estudante> listEstudante = estudanteService.readAll();
        return ResponseEntity.ok(listEstudante);
    }
    //===== UPDATE =====
    @PutMapping("/{id}")
    public ResponseEntity<Estudante> update(@PathVariable Long id, @RequestBody EstudanteRequestDto estudante){
        Estudante estudanteAtualizado = estudanteService.update(id , estudante);
        return ResponseEntity.ok(estudanteAtualizado);
    }
    //===== DELETE =====
    @DeleteMapping("/{id}/Fisico")
    public ResponseEntity<Void> harDelete(@PathVariable Long id){
        estudanteService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id){
        estudanteService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    //====== metodo para reativar a conta ======
    @PutMapping("/{id}/reativar")
    public ResponseEntity<Estudante> reactiveEstudante(@PathVariable Long id){
        Estudante estudanteReativado = estudanteService.reactiveEstudante(id);
        return ResponseEntity.ok(estudanteReativado);
    }
}
