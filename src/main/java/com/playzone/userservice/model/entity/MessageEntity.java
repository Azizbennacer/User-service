package com.playzone.userservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class MessageEntity {

  @Id
  private UUID id;

  @Column(name = "sender_id", nullable = false)
  private UUID senderId;

  @Column(name = "receiver_id", nullable = false)
  private UUID receiverId;

  @Column(name = "post_id", nullable = false)
  private UUID postId;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String text;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @PrePersist
  public void prePersist() {
    if (id == null) id = UUID.randomUUID();
    if (createdAt == null) createdAt = Instant.now();
  }

  public MessageEntity(UUID senderId, UUID receiverId, UUID postId, String text) {
    this.id = UUID.randomUUID();
    this.senderId = senderId;
    this.receiverId = receiverId;
    this.postId = postId;
    this.text = text;
    this.createdAt = Instant.now();
  }
}
