package br.com.ludevsp.domain.exceptions;

import br.com.ludevsp.domain.dto.ApiResponse;
import br.com.ludevsp.domain.exceptions.dto.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<ErroDTO>> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ApiResponse<>(new ErroDTO(ex.getMessage(), 404), false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ApiResponse<ErroDTO>> handleInvalidParameterException(InvalidParameterException ex) {
        return new ResponseEntity<>(new ApiResponse<>(new ErroDTO(ex.getMessage(), 400), false), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ServerExceptionError.class)
    public ResponseEntity<ApiResponse<ErroDTO>> handleServerExceptionError(ServerExceptionError ex) {
        return new ResponseEntity<>(new ApiResponse<>(new ErroDTO(ex.getMessage(), 503), false), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}