package com.innovetsolutionstech.taskearnersng.tasks_service.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record NewTaskActivity(

        @NotNull(message = "Task ID is required")
        String taskID,

        @NotNull(message = "Task completed by is required")
        String taskCompletedBy,

        @NotNull(message = "Social Media Account is required")
        String socialMediaAccount,

        @NotNull(message = "Task Description is required")
        MultipartFile proofOfWork
) {
}
