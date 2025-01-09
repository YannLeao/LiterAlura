package br.com.yann.literalura.model;

import java.util.List;

public record ApiResponse(
        List<DataBook> results) {
}
