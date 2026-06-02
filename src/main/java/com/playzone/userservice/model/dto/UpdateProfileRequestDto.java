package com.playzone.userservice.model.dto;

import jakarta.validation.constraints.Size;

public record UpdateProfileRequestDto(
    @Size(max = 255) String displayName,
    @Size(max = 20) String phone,
    @Size(max = 100) String city,
    @Size(max = 1000) String bio
) {}
