package br.com.yann.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataAuthor(String name,
                         @JsonAlias("birth_year") int birthYear,
                         @JsonAlias("death_year") int deathYear) {
}
