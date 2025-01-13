package com.innovetsolutionstech.taskearnersng.tasks_service.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record NewTaskType(

        @NotNull(message = "Task name is required")
        String taskName,

        @NotNull(message = "Task Description is required")
        String taskDescription,

        @NotNull(message = "Task Price is required")
        Long taskPrice,

        @NotNull(message = "Price type is required")
        String priceType,

        @NotNull(message = "Platform is required")
        String platform,

        @NotNull(message = "App Name is required")
        String appName
) {
}
