package fipeapi.desafio.alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record ModelosAnos(@JsonAlias("codigo") String codigo,
                          @JsonAlias("nome") String nome) {
}
