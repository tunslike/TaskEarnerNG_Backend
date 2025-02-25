package com.innovetsolutionstech.taskearnersng.subscriber_service.model.dto;

public record LoginResponse(
        String subscriberId,
        String username,
        String firstname,
        String lastname,
        String authType,
        String token,
        String refreshToken,
        Integer status

) {
}
