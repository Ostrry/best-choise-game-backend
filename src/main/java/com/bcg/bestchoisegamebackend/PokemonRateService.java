package com.bcg.bestchoisegamebackend;

import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class PokemonRateService {

    private final RandomIdPairsGenerator randomIdPairsGenerator;

    public PokemonRateService(RandomIdPairsGenerator randomIdPairsGenerator) {
        this.randomIdPairsGenerator = randomIdPairsGenerator;

    }

    public QuestionResponse generateQuestion() {
        UUID uuid = UUID.randomUUID();
        PokemonIdsPairs pokemonIdsPairs = randomIdPairsGenerator.generate();
        try {
            QuestionDBService.insertUser(uuid, pokemonIdsPairs.id1(),pokemonIdsPairs.id2());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new QuestionResponse(uuid, pokemonIdsPairs.id1(), pokemonIdsPairs.id2());
    }
}
