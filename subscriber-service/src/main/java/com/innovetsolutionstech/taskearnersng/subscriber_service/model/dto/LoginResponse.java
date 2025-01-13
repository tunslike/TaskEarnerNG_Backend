package com.innovetsolutionstech.taskearnersng.subscriber_service.model.dto;

public record LoginResponse(
        String subscriberId,
        String username,
        String firstname,
        String lastname,
        String authType,
        Integer status

) {
}
