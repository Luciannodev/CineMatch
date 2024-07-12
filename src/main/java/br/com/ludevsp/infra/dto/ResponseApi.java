package br.com.ludevsp.infra.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseApi<T> {
    @JsonProperty("page")
    private int page;

    @JsonProperty("results")
    private List<T> data;

    @JsonProperty("total_results")
    private int totalResults;

    @JsonProperty("total_pages")
    private int totalPages;
}
