package com.playzone.userservice.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

public record CreateMatchmakingPostRequestDto(
    @NotBlank @Size(max = 20) String intent,
    @NotBlank @Size(max = 20) String sport,
    @NotBlank @Size(max = 120) String city,
    @NotNull LocalDate matchDate,
    @NotNull LocalTime matchTime,
    @NotBlank @Size(max = 40) String level,
    @NotNull @Min(1) @Max(22) Integer spots,
    @NotBlank @Size(max = 1200) String note
) {}
