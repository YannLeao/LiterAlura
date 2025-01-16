package br.com.yann.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData{
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obtainData(String json, Class<T> object){
        try {
            return mapper.readValue(json, object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
