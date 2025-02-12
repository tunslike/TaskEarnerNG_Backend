package com.innovetsolutionstech.taskearnersng.tasks_service.model.dto;

public record NewTaskResponse(
        String newTaskID,
        Integer status,
        String Message
) {
}
