package com.innovetsolutionstech.taskearnersng.tasks_service.model.dto;

public record SubscribedTaskDto(

        String taskId,
        String platform,
        String taskName,
        String icon,
        String taskType,
        Double price,
        Integer NoOfPost,
        String dateCreated,
        Integer paymentStatus,
        Integer status
) {
}
