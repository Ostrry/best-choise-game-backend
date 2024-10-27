package com.bcg.bestchoisegamebackend.rest;


import java.util.UUID;

public record QuestionResponse(UUID id, int firstPkmId, int secondPkmId) {
}
