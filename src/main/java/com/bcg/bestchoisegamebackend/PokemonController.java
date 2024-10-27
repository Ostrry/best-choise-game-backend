package com.bcg.bestchoisegamebackend;

import com.bcg.bestchoisegamebackend.rest.AnswerRequestBody;
import com.bcg.bestchoisegamebackend.rest.QuestionResponse;
import com.bcg.bestchoisegamebackend.service.PokemonRateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonController {

    private final PokemonRateService pokemonRateService;

    public PokemonController(PokemonRateService pokemonRateService) {
        this.pokemonRateService = pokemonRateService;
    }

    @GetMapping("/question")
    public QuestionResponse question() {
        return pokemonRateService.generateQuestion();
    }

    @PostMapping("/answer")
        public void answer(@RequestBody AnswerRequestBody answerRequestBody){
            pokemonRateService.processAnswer(answerRequestBody.uuid(), answerRequestBody.winnerId());
        }
}
