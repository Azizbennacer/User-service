package com.playzone.userservice.service;

import com.playzone.userservice.model.dto.CreateMatchmakingPostRequestDto;
import com.playzone.userservice.model.dto.MatchmakingPostDto;
import com.playzone.userservice.model.entity.MatchmakingPostEntity;
import com.playzone.userservice.repository.MatchmakingPostRepository;
import com.playzone.userservice.util.UserPrincipal;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchmakingService {

    private final MatchmakingPostRepository repository;

    public MatchmakingService(MatchmakingPostRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public MatchmakingPostDto create(CreateMatchmakingPostRequestDto request, UserPrincipal principal) {
        MatchmakingPostEntity entity = new MatchmakingPostEntity();
        entity.setCreatorUserId(principal.getUserId());
        entity.setCreatorName(principal.getFullName());
        entity.setIntent(normalizeIntent(request.intent()));
        entity.setSport(normalizeSport(request.sport()));
        entity.setCity(request.city().trim());
        entity.setMatchDate(request.matchDate());
        entity.setMatchTime(request.matchTime());
        entity.setLevel(request.level().trim());
        entity.setSpots(request.spots());
        entity.setNote(request.note().trim());
        entity.setStatus("OPEN");

        return toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public List<MatchmakingPostDto> listOpen(String sport, String intent) {
        String s = normalizeOptionalSport(sport);
        String i = normalizeOptionalIntent(intent);

        if (s != null && i != null) {
            return repository.findByStatusAndSportAndIntentOrderByCreatedAtDesc("OPEN", s, i).stream()
                .map(this::toDto)
                .toList();
        }
        if (s != null) {
            return repository.findByStatusAndSportOrderByCreatedAtDesc("OPEN", s).stream().map(this::toDto).toList();
        }
        if (i != null) {
            return repository.findByStatusAndIntentOrderByCreatedAtDesc("OPEN", i).stream().map(this::toDto).toList();
        }
        return repository.findByStatusOrderByCreatedAtDesc("OPEN").stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<MatchmakingPostDto> listMine(UUID userId) {
        return repository.findByCreatorUserIdOrderByCreatedAtDesc(userId).stream().map(this::toDto).toList();
    }

    private String normalizeSport(String sport) {
        if (sport == null || sport.isBlank()) {
            throw new IllegalArgumentException("sport is required");
        }
        String normalized = sport.trim();
        if (!List.of("Football", "Padel", "Tennis").contains(normalized)) {
            throw new IllegalArgumentException("sport must be Football, Padel or Tennis");
        }
        return normalized;
    }

    private String normalizeIntent(String intent) {
        if (intent == null || intent.isBlank()) {
            throw new IllegalArgumentException("intent is required");
        }
        String normalized = intent.trim().toUpperCase(Locale.ROOT);
        if (!List.of("ORGANIZE", "JOIN", "TEAM", "PARTNER").contains(normalized)) {
            throw new IllegalArgumentException("intent must be ORGANIZE, JOIN, TEAM or PARTNER");
        }
        return normalized;
    }

    private String normalizeOptionalSport(String sport) {
        return sport == null || sport.isBlank() ? null : normalizeSport(sport);
    }

    private String normalizeOptionalIntent(String intent) {
        return intent == null || intent.isBlank() ? null : normalizeIntent(intent);
    }

    private MatchmakingPostDto toDto(MatchmakingPostEntity entity) {
        return new MatchmakingPostDto(
            entity.getId().toString(),
            entity.getCreatorUserId().toString(),
            entity.getCreatorName(),
            entity.getIntent(),
            entity.getSport(),
            entity.getCity(),
            entity.getMatchDate(),
            entity.getMatchTime(),
            entity.getLevel(),
            entity.getSpots(),
            entity.getNote(),
            entity.getStatus(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
