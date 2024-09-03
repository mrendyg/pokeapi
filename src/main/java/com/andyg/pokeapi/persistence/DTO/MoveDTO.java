package com.andyg.pokeapi.persistence.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoveDTO {

    @JsonProperty("name")
    private String moveName;

    @JsonProperty("power")
    private Integer movePower;
}
