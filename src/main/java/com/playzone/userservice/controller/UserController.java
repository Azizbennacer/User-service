package com.playzone.userservice.controller;

import com.playzone.userservice.model.dto.UpdateProfileRequestDto;
import com.playzone.userservice.model.dto.UserProfileDto;
import com.playzone.userservice.service.UserProfileService;
import com.playzone.userservice.util.UserPrincipal;
import jakarta.validation.Valid;
import java.io.IOException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserProfileService userProfileService;

    public UserController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getMe(@AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(userProfileService.getOrCreateProfile(
            principal.getUserId(), principal.getUsername(),
            principal.getFullName(), principal.getLanguage()
        ));
    }

    @PutMapping("/me")
    public ResponseEntity<UserProfileDto> updateMe(
        @AuthenticationPrincipal UserPrincipal principal,
        @Valid @RequestBody UpdateProfileRequestDto request
    ) {
        return ResponseEntity.ok(userProfileService.updateProfile(
            principal.getUserId(), request, principal.getLanguage()
        ));
    }

    @PostMapping("/me/photo")
    public ResponseEntity<UserProfileDto> uploadPhoto(
        @AuthenticationPrincipal UserPrincipal principal,
        @RequestParam("file") MultipartFile file
    ) throws IOException {
        return ResponseEntity.ok(userProfileService.uploadPhoto(
            principal.getUserId(), file, principal.getLanguage()
        ));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserProfileDto>> listUsers(
        @PageableDefault(size = 20, sort = "email") Pageable pageable
    ) {
        return ResponseEntity.ok(userProfileService.listProfiles(pageable));
    }
}
