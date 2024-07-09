package br.com.ludevsp.domain.exceptions.dto;

import lombok.Data;

@Data
public class ErroDTO {
    private String mensagem;
    private Number codeError;

    public ErroDTO(String mensagem, Number codeError) {
        this.mensagem = mensagem;
        this.codeError = codeError;
    }
}
