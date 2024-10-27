package com.bcg.bestchoisegamebackend.validator;

public record PokemonIdsPairs(int id1, int id2) {

    public PokemonIdsPairs {
        if (id1 == id2){
            throw new IllegalArgumentException("Pokemon ids pairs are equal");
        }
        if (id1 < 1 || id2 < 1 || id1 > 150 || id2 > 150) {
            throw new IllegalArgumentException("Invalid pokemon ids");
        }
    }
}
