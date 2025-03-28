package com.innovetsolutionstech.taskearnersng.tasks_service.model.dto;

public record TaskResponse(
        String taskId,
        String taskCategory,
        String taskType,
        String taskTypeId,
        String taskName,
        String platform,
        Double taskPrice,
        String captionMessage,
        String taskIcon,
        String taskThumbnail,
        String dateCreated
) {
}
