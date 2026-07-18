package com.projetoIntegrador.Studia.dto;
import java.time.LocalDateTime;

public record CronogramaRequestDto(Long estudanteId, Long tarefaEstudoId,LocalDateTime dataAgendada, String status){
}
