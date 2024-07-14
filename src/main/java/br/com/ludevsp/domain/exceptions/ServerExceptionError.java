package br.com.ludevsp.domain.exceptions;

public class ServerExceptionError extends RuntimeException{
    public ServerExceptionError(String message) {
        super(message);
    }
}
