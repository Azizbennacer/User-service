package com.playzone.userservice.service;

import com.playzone.userservice.model.dto.ConversationSummaryDto;
import com.playzone.userservice.model.dto.MessageDto;
import com.playzone.userservice.model.entity.MessageEntity;
import com.playzone.userservice.repository.MatchmakingPostRepository;
import com.playzone.userservice.repository.MessageRepository;
import com.playzone.userservice.repository.UserProfileRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {

  private final MessageRepository messageRepository;
  private final MatchmakingPostRepository postRepository;
  private final UserProfileRepository userRepository;

  public MessageService(
      MessageRepository messageRepository,
      MatchmakingPostRepository postRepository,
      UserProfileRepository userRepository) {
    this.messageRepository = messageRepository;
    this.postRepository = postRepository;
    this.userRepository = userRepository;
  }

  @Transactional
  public MessageDto sendMessage(UUID senderId, UUID receiverId, UUID postId, String text) {
    MessageEntity entity = new MessageEntity(senderId, receiverId, postId, text.trim());
    return toDto(messageRepository.save(entity));
  }

  @Transactional(readOnly = true)
  public List<MessageDto> getConversation(UUID postId, UUID userId1, UUID userId2) {
    return messageRepository.findConversation(postId, userId1, userId2).stream()
        .map(this::toDto)
        .toList();
  }

  @Transactional(readOnly = true)
  public List<MessageDto> getReceivedMessages(UUID receiverId) {
    return messageRepository.findByReceiverIdOrderByCreatedAtDesc(receiverId).stream()
        .map(this::toDto)
        .toList();
  }

  @Transactional(readOnly = true)
  public List<ConversationSummaryDto> getInbox(UUID receiverId) {
    List<Object[]> pairs = messageRepository.findDistinctConversationsForReceiver(receiverId);
    List<ConversationSummaryDto> result = new ArrayList<>();

    for (Object[] row : pairs) {
      UUID postId = (UUID) row[0];
      UUID senderId = (UUID) row[1];

      // Get messages for this conversation
      List<MessageDto> messages =
          messageRepository.findAllByPostAndParticipants(postId, senderId, receiverId).stream()
              .map(this::toDto)
              .toList();

      // Get post info
      String sport = "Sport";
      String city = "";
      String note = "";
      var postOpt = postRepository.findById(postId);
      if (postOpt.isPresent()) {
        var post = postOpt.get();
        sport = post.getSport();
        city = post.getCity();
        note = post.getNote();
      }

      // Get sender name
      String senderName = "Utilisateur";
      var senderOpt = userRepository.findById(senderId);
      if (senderOpt.isPresent()) {
        var sender = senderOpt.get();
        senderName = sender.getDisplayName() != null ? sender.getDisplayName() : sender.getEmail();
      }

      result.add(new ConversationSummaryDto(
          postId.toString(),
          senderId.toString(),
          senderName,
          sport,
          city,
          note,
          messages));
    }

    return result;
  }

  private MessageDto toDto(MessageEntity entity) {
    return new MessageDto(
        entity.getId().toString(),
        entity.getSenderId().toString(),
        entity.getReceiverId().toString(),
        entity.getPostId().toString(),
        entity.getText(),
        entity.getCreatedAt());
  }
}
