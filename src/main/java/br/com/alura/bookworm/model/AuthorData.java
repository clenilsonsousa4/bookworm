package br.com.alura.bookworm.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorData(String name, @JsonAlias("birth_year") String birthYear, @JsonAlias("death_year") String deathYear) {
}
