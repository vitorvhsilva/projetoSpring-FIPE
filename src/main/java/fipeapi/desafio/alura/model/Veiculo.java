package fipeapi.desafio.alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(@JsonAlias("Modelo") String nome,
                      @JsonAlias("Valor") String valor,
                      @JsonAlias("Marca") String marca) {
}
