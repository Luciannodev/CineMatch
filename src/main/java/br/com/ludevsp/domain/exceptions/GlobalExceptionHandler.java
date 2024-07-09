package br.com.ludevsp.domain.exceptions;

import br.com.ludevsp.domain.exceptions.dto.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErroDTO> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErroDTO(ex.getMessage(),404), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ErroDTO> handleInvalidParameterException(InvalidParameterException ex) {
        return new ResponseEntity<>(new ErroDTO(ex.getMessage(),400), HttpStatus.BAD_REQUEST);
    }

}