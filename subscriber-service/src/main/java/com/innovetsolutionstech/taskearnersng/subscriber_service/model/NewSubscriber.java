package com.innovetsolutionstech.taskearnersng.subscriber_service.model;

import com.innovetsolutionstech.taskearnersng.subscriber_service.enums.AuthType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record NewSubscriber(

        @NotNull(message = "Username is required")
        @Email(message = "Username must be a valid email")
        String username,

        @NotNull(message = "Lastname is required")
        String accessCode,

        @NotNull(message = "Lastname is required")
        String lastName,

        @NotNull(message = "Firstname is required")
        String firstName,

        String authToken,

        @Enumerated(EnumType.STRING)
        AuthType authType,

        @NotNull
        String ipaddress
) {
}
