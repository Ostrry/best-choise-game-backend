package com.bcg.bestchoisegamebackend.service;

import com.bcg.bestchoisegamebackend.dbservice.PokemonDBService;
import com.bcg.bestchoisegamebackend.dbservice.QuestionDBService;
import com.bcg.bestchoisegamebackend.entity.PokemonModel;
import com.bcg.bestchoisegamebackend.entity.QuestionModel;
import com.bcg.bestchoisegamebackend.rest.QuestionResponse;
import com.bcg.bestchoisegamebackend.validator.PokemonIdsPairs;
//import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PokemonRateService {

    private final RandomIdPairsGenerator randomIdPairsGenerator;
    private PokemonDBService pokemonRepository;

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

    public void processAnswer(UUID uuid, int winnerId) {
        QuestionModel questionEntity = null;
        try {
            if (!QuestionDBService.isExist(uuid)){
                throw new RuntimeException("Question does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            questionEntity = QuestionDBService.getById(uuid);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (questionEntity != null && winnerId!= questionEntity.getId1() && winnerId!= questionEntity.getId2()) {
            throw new RuntimeException("Winner id does not match");
        }

        PokemonIdsPairs pair = new PokemonIdsPairs(questionEntity.getId1(), questionEntity.getId2());
        Optional<PokemonModel> firstPkm = Optional.empty();
        Optional<PokemonModel> secondPkm = Optional.empty();
        try {
            firstPkm = pokemonRepository.getById(pair.id1());
            secondPkm = pokemonRepository.getById(pair.id2());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (firstPkm.isEmpty() || secondPkm.isEmpty()) {
            throw new RuntimeException("Pokemon does not exist");
        }



        QuestionDBService.deleteQuestion(uuid);

    }
}
