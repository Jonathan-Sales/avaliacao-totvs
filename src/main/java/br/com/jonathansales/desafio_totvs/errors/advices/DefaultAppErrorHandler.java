package br.com.jonathansales.desafio_totvs.errors.advices;

import br.com.jonathansales.desafio_totvs.errors.DefaultAppException;
import br.com.jonathansales.desafio_totvs.errors.dtos.DefaultAppErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultAppErrorHandler {

    @ExceptionHandler(DefaultAppException.class)
    public ResponseEntity<DefaultAppErrorDTO> handle(DefaultAppException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(new DefaultAppErrorDTO(exception.getMessage()));
    }

}
