package br.com.yann.literalura.service;

public interface IConvertData {
    <T> T obtainData(String json, Class<T> object);
}
