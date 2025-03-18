package com.innovetsolutionstech.taskearnersng.tasks_service.model.dto;

public record CompletedTaskResponse(
        String taskId,
        String platform,
        String taskName,
        String taskType,
        String taskThumbnail,
        Double price,
        String dateCompleted
) {
}
