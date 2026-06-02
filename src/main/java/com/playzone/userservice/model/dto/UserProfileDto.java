package com.playzone.userservice.model.dto;

public record UserProfileDto(
    String id,
    String email,
    String displayName,
    String phone,
    String city,
    String bio,
    String photoUrl,
    String language
) {}
