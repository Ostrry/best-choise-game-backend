package com.bcg.bestchoisegamebackend.rest;

import java.util.UUID;

public record AnswerRequestBody(UUID uuid, int winnerId) {
}
