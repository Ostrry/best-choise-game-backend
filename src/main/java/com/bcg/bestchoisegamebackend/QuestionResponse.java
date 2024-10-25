package com.bcg.bestchoisegamebackend;


import java.util.UUID;

public record QuestionResponse(UUID id, int firstPkmId, int secondPkmId) {
}
