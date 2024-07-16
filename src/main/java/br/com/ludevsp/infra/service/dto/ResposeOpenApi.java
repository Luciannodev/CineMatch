package br.com.ludevsp.infra.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResposeOpenApi <T> {
    @JsonProperty("choices")
    private T choices;
}
