package com.innovetsolutionstech.taskearnersng.tasks_service.model;

import jakarta.validation.constraints.NotNull;

public record TaskSessionModel(
        @NotNull(message = "Subscriber ID is required")
        String subscriberId,

        @NotNull(message = "Task ID is required")
        String taskID
) {
}
