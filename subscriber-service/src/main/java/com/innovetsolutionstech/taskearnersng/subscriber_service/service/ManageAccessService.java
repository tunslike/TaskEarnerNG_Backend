package com.innovetsolutionstech.taskearnersng.subscriber_service.service;

import com.innovetsolutionstech.taskearnersng.subscriber_service.data.ManageAccessDataRepository;
import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.ManageAccess;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.util.UUID;

@Service
public class ManageAccessService {
    private final ManageAccessDataRepository manageAccessDataRepository;

    public ManageAccessService(ManageAccessDataRepository manageAccessDataRepository) {
        this.manageAccessDataRepository = manageAccessDataRepository;
    }

    public ManageAccess createRefreshToken(String subscriberId) {
        Instant expirationInstant = Instant.now().plusSeconds(7 * 24 * 60 * 60);

        ManageAccess refreshToken = new ManageAccess();
            refreshToken.setToken_id(UUID.randomUUID().toString());
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(LocalDateTime.ofInstant(expirationInstant, ZoneId.systemDefault()));
            refreshToken.setSubscriberId(subscriberId);
            refreshToken.setDateCreated(LocalDateTime.now());
        return manageAccessDataRepository.save(refreshToken);

    }

    public ManageAccess verifyRefreshToken(String token) {
        return manageAccessDataRepository.findByToken(token)
                .filter(rt -> rt.getExpiryDate().isAfter(ChronoLocalDateTime.from(Instant.now())))
                .orElseThrow(() -> new RuntimeException("Invalid or expired refresh token"));
    }

    public void deleteRefreshToken(String subscriberId) {
        manageAccessDataRepository.deleteBySubscriberId(subscriberId);
    }

}
