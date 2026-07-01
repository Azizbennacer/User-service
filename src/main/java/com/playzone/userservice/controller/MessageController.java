package com.playzone.userservice.controller;

import com.playzone.userservice.model.dto.ConversationSummaryDto;
import com.playzone.userservice.model.dto.MessageDto;
import com.playzone.userservice.service.MessageService;
import com.playzone.userservice.util.UserPrincipal;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @PostMapping
  public ResponseEntity<MessageDto> sendMessage(
      @AuthenticationPrincipal UserPrincipal principal,
      @RequestBody SendMessageRequestDto request) {
    UserPrincipal authUser = requirePrincipal(principal);
    return ResponseEntity.ok(
        messageService.sendMessage(
        authUser.getUserId(),
            UUID.fromString(request.receiverId()),
            UUID.fromString(request.postId()),
            request.text()));
  }

  @GetMapping
  public ResponseEntity<List<MessageDto>> getConversation(
      @AuthenticationPrincipal UserPrincipal principal,
      @RequestParam String postId,
      @RequestParam String otherUserId) {
    UserPrincipal authUser = requirePrincipal(principal);
    return ResponseEntity.ok(
        messageService.getConversation(
            UUID.fromString(postId),
        authUser.getUserId(),
            UUID.fromString(otherUserId)));
  }

  @GetMapping("/inbox")
  public ResponseEntity<List<ConversationSummaryDto>> getInbox(
      @AuthenticationPrincipal UserPrincipal principal) {
    UserPrincipal authUser = requirePrincipal(principal);
    return ResponseEntity.ok(messageService.getInbox(authUser.getUserId()));
  }

  private UserPrincipal requirePrincipal(UserPrincipal principal) {
    if (principal == null || principal.getUserId() == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Utilisateur non authentifié");
    }
    return principal;
  }

  public record SendMessageRequestDto(String receiverId, String postId, String text) {}
}
