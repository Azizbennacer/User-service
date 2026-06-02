package com.playzone.userservice.service;

import com.playzone.userservice.exception.ResourceNotFoundException;
import com.playzone.userservice.model.dto.UpdateProfileRequestDto;
import com.playzone.userservice.model.dto.UserProfileDto;
import com.playzone.userservice.model.entity.UserProfileEntity;
import com.playzone.userservice.repository.UserProfileRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserProfileService {

    private final UserProfileRepository repository;
    private final String uploadsDir;

    public UserProfileService(
        UserProfileRepository repository,
        @Value("${app.uploads.dir:uploads/photos}") String uploadsDir
    ) {
        this.repository = repository;
        this.uploadsDir = uploadsDir;
    }

    @Transactional
    public UserProfileDto getOrCreateProfile(UUID userId, String email, String fullName, String language) {
        UserProfileEntity profile = repository.findById(userId).orElseGet(() -> {
            UserProfileEntity p = new UserProfileEntity();
            p.setId(userId);
            p.setEmail(email);
            p.setDisplayName(fullName);
            return repository.save(p);
        });
        return toDto(profile, language);
    }

    @Transactional
    public UserProfileDto updateProfile(UUID userId, UpdateProfileRequestDto dto, String language) {
        UserProfileEntity profile = repository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User profile not found"));
        if (dto.displayName() != null) profile.setDisplayName(dto.displayName());
        if (dto.phone() != null) profile.setPhone(dto.phone());
        if (dto.city() != null) profile.setCity(dto.city());
        if (dto.bio() != null) profile.setBio(dto.bio());
        return toDto(repository.save(profile), language);
    }

    @Transactional
    public UserProfileDto uploadPhoto(UUID userId, MultipartFile file, String language) throws IOException {
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("File must be an image (jpeg, png, webp)");
        }
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new IllegalArgumentException("Image size must not exceed 5 MB");
        }

        String ext = getExtension(file.getOriginalFilename());
        String filename = userId + "_" + System.currentTimeMillis() + ext;
        Path destDir = Paths.get(uploadsDir);
        Files.createDirectories(destDir);
        file.transferTo(destDir.resolve(filename));

        UserProfileEntity profile = repository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User profile not found"));
        profile.setPhotoUrl("/uploads/photos/" + filename);
        return toDto(repository.save(profile), language);
    }

    @Transactional(readOnly = true)
    public Page<UserProfileDto> listProfiles(Pageable pageable) {
        return repository.findAll(pageable).map(p -> toDto(p, null));
    }

    private String getExtension(String filename) {
        if (filename == null) return ".jpg";
        int dot = filename.lastIndexOf('.');
        return dot >= 0 ? filename.substring(dot).toLowerCase() : ".jpg";
    }

    private UserProfileDto toDto(UserProfileEntity p, String language) {
        return new UserProfileDto(
            p.getId().toString(),
            p.getEmail(),
            p.getDisplayName(),
            p.getPhone(),
            p.getCity(),
            p.getBio(),
            p.getPhotoUrl(),
            language
        );
    }
}
