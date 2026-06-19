package com.playzone.userservice.model.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

public record MatchmakingPostDto(
    String id,
    String creatorUserId,
    String creatorName,
    String intent,
    String sport,
    String city,
    LocalDate matchDate,
    LocalTime matchTime,
    String level,
    Integer spots,
    String note,
    String status,
    Instant createdAt,
    Instant updatedAt
) {}
