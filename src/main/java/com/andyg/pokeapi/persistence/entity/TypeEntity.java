package com.andyg.pokeapi.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "type")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String name;

}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class TypeSlot {

    @ManyToOne
    private TypeEntity type;

}


