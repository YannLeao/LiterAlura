package br.com.yann.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConvertData {
    <T> T obtainData(String json, Class<T> object) throws JsonProcessingException;
}
