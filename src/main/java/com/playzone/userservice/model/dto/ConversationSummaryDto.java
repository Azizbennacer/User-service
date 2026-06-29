package com.playzone.userservice.model.dto;

import java.time.Instant;
import java.util.List;

public record ConversationSummaryDto(
    String postId,
    String senderId,
    String senderName,
    String sport,
    String city,
    String note,
    List<MessageDto> messages
) {}
