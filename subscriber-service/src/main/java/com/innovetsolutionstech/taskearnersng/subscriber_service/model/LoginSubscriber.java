package com.innovetsolutionstech.taskearnersng.subscriber_service.model;

import jakarta.validation.constraints.NotNull;

public record LoginSubscriber(

        @NotNull(message = "Subscriber Id is required")
        String subscriberId,

        @NotNull(message = "Access Code is required")
        String accessCode
) {
}
