package com.playzone.userservice.repository;

import com.playzone.userservice.model.entity.MatchmakingPostEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchmakingPostRepository extends JpaRepository<MatchmakingPostEntity, UUID> {

    List<MatchmakingPostEntity> findByStatusOrderByCreatedAtDesc(String status);

    List<MatchmakingPostEntity> findByStatusAndSportOrderByCreatedAtDesc(String status, String sport);

    List<MatchmakingPostEntity> findByStatusAndIntentOrderByCreatedAtDesc(String status, String intent);

    List<MatchmakingPostEntity> findByStatusAndSportAndIntentOrderByCreatedAtDesc(String status, String sport, String intent);

    List<MatchmakingPostEntity> findByCreatorUserIdOrderByCreatedAtDesc(UUID creatorUserId);
}
