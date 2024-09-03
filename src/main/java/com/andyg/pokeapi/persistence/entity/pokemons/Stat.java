package com.andyg.pokeapi.persistence.entity.pokemons;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Stat{
    private int base_stat;

    @ManyToOne
    private StatsEntity stat;

}
