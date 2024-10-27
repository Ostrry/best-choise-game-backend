package com.bcg.bestchoisegamebackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Pokemons")
@NoArgsConstructor
public class PokemonModel {
    @Id
    private int Id;
    private String Name;
    private Integer Rating;
}

