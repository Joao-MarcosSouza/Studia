package com.projetoIntegrador.Studia.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CronogramaTarefa {
    private String status;
    private Boolean concluido;
}
