package com.playzone.userservice.repository;

import com.playzone.userservice.model.entity.MessageEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {

  @Query(
      "SELECT m FROM MessageEntity m WHERE m.postId = :postId "
          + "AND ((m.senderId = :userId1 AND m.receiverId = :userId2) "
          + "OR (m.senderId = :userId2 AND m.receiverId = :userId1)) "
          + "ORDER BY m.createdAt ASC")
  List<MessageEntity> findConversation(
      @Param("postId") UUID postId, @Param("userId1") UUID userId1, @Param("userId2") UUID userId2);

  List<MessageEntity> findByPostIdAndReceiverIdOrderByCreatedAtDesc(UUID postId, UUID receiverId);

  List<MessageEntity> findByReceiverIdOrderByCreatedAtDesc(UUID receiverId);

  /** Find all distinct (postId, senderId) pairs where current user received messages */
  @Query("SELECT DISTINCT m.postId, m.senderId FROM MessageEntity m WHERE m.receiverId = :receiverId")
  List<Object[]> findDistinctConversationsForReceiver(@Param("receiverId") UUID receiverId);

  @Query("SELECT m FROM MessageEntity m WHERE m.postId = :postId "
      + "AND m.senderId = :senderId AND m.receiverId = :receiverId ORDER BY m.createdAt ASC")
  List<MessageEntity> findAllByPostAndParticipants(
      @Param("postId") UUID postId,
      @Param("senderId") UUID senderId,
      @Param("receiverId") UUID receiverId);
}
