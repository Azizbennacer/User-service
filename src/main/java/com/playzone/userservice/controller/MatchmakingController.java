package com.playzone.userservice.controller;

import com.playzone.userservice.model.dto.CreateMatchmakingPostRequestDto;
import com.playzone.userservice.model.dto.MatchmakingPostDto;
import com.playzone.userservice.service.MatchmakingService;
import com.playzone.userservice.util.UserPrincipal;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/matchmaking")
public class MatchmakingController {

    private final MatchmakingService matchmakingService;

    public MatchmakingController(MatchmakingService matchmakingService) {
        this.matchmakingService = matchmakingService;
    }

    @PostMapping
    public ResponseEntity<MatchmakingPostDto> create(
        @AuthenticationPrincipal UserPrincipal principal,
        @Valid @RequestBody CreateMatchmakingPostRequestDto request
    ) {
        return ResponseEntity.ok(matchmakingService.create(request, principal));
    }

    @GetMapping
    public ResponseEntity<List<MatchmakingPostDto>> list(
        @RequestParam(required = false) String sport,
        @RequestParam(required = false) String intent
    ) {
        return ResponseEntity.ok(matchmakingService.listOpen(sport, intent));
    }

    @GetMapping("/my")
    public ResponseEntity<List<MatchmakingPostDto>> listMine(
        @AuthenticationPrincipal UserPrincipal principal
    ) {
        return ResponseEntity.ok(matchmakingService.listMine(principal.getUserId()));
    }
}
