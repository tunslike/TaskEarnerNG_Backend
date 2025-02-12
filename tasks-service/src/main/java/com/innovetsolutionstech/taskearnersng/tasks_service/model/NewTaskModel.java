package com.innovetsolutionstech.taskearnersng.tasks_service.model;

import jakarta.validation.constraints.NotNull;

public record NewTaskModel(

        @NotNull(message = "Task Type ID is required")
        String taskTypeID,

        @NotNull(message = "Task type is required")
        String taskType,

        @NotNull(message = "Task name is required")
        String taskName,

        @NotNull(message = "Task platform is required")
        String platform,

        String caption_message,

        String engagement_type,

        String social_media_link,

        double price,

        @NotNull(message = "No of Post is required")
        Integer no_of_post,

        @NotNull(message = "Gender is required")
        String gender,

        @NotNull(message = "Location is required")
        String location


) {
}
