package com.projetoIntegrador.Studia.repository;

import com.projetoIntegrador.Studia.model.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudanteRepository extends JpaRepository<Estudante,Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
