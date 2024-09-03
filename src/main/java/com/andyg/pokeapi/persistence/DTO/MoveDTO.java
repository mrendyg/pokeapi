package com.andyg.pokeapi.persistence.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoveDTO {

    //Se creo el MoveDTO para traer a la vista el power de cada movimento para filtrar los 3 mas poderosos

    @JsonProperty("name")
    private String moveName;

    @JsonProperty("power")
    private Integer movePower;
}
