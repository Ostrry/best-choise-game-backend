package com.bcg.bestchoisegamebackend.service;

import com.bcg.bestchoisegamebackend.validator.PokemonIdsPairs;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class RandomIdPairsGenerator {

    private final static SecureRandom random = new SecureRandom();

    public PokemonIdsPairs generate(){
        int id1 = randomId();
        int id2 = generateRandomExceptId1(id1);
        return new PokemonIdsPairs(id1, id2);
    }

    private int generateRandomExceptId1(int id1) {
        int id2 = randomId();
        if (id1 != id2){
            return id2;
        }
        return generateRandomExceptId1(id1);
    }

    private int randomId() {
        return random.nextInt(151) + 1;
    }
}
