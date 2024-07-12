package br.com.ludevsp.infra.mapper;

import br.com.ludevsp.infra.dto.MovieDto;
import br.com.ludevsp.infra.dto.ResponseApi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseToEntityMapper {
    private ObjectMapper mapper;

    public ResponseToEntityMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public <T> T responseToEntity(ResponseEntity<String> response, TypeReference<T> type) {
        T entityModel = null;
        try {
            entityModel = mapper.readValue(response.getBody(), type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityModel;
    }
}
