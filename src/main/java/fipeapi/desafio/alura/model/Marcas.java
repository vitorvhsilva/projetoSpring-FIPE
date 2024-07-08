package fipeapi.desafio.alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Marcas (@JsonAlias("codigo") String codigo,
                      @JsonAlias("nome") String nome) {
}
