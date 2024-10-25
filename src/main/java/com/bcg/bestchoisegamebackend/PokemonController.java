package com.bcg.bestchoisegamebackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
