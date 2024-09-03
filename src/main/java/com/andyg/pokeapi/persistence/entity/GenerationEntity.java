package com.andyg.pokeapi.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class GenerationEntity {


    private String name;
    @OneToMany
    @JoinColumn(name = "generation_id")
    private List<PokemonSpeciesEntity> pokemon_species;

}

