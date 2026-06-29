package com.playzone.userservice.model.dto;

import java.time.Instant;

public record MessageDto(
    String id,
    String senderId,
    String receiverId,
    String postId,
    String text,
    Instant createdAt
) {}
