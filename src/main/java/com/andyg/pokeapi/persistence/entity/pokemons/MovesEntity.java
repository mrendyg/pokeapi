package com.andyg.pokeapi.persistence.entity.pokemons;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "moves")
public class MovesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String name;

    private String url;
}


