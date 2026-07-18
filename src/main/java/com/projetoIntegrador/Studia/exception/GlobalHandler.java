package com.projetoIntegrador.Studia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(ErrodeDeValidacaoException.class)
    public ProblemDetail handleErroDeValidacaoException(ErrodeDeValidacaoException erro){
        ProblemDetail detalhes = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, erro.getMessage());
        detalhes.setTitle("Campo invalido");

        return detalhes;
    }

    @ExceptionHandler(RecursoNaoEncotradoException.class)
    public ProblemDetail handleRecursoNaoEncontradoException(RecursoNaoEncotradoException erro){
        ProblemDetail detalhes = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, erro.getMessage());
        detalhes.setTitle("Recurso não encontrado");

        return detalhes;
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    public ProblemDetail handleRecursoDuplicadoException(RecursoDuplicadoException erro){
        ProblemDetail detalhes = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, erro.getMessage());
        detalhes.setTitle("Recurso já existente");

        return detalhes;
    }

    @ExceptionHandler(EstadoInvalidoException.class)
    public ProblemDetail handleEstadoInvalidadeException(EstadoInvalidoException erro){
        ProblemDetail detalhes = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_CONTENT, erro.getMessage());
        detalhes.setTitle("Conflito de Estado");
        detalhes.setDetail("Recurso ja Ativo");

        return detalhes;
    }
}
