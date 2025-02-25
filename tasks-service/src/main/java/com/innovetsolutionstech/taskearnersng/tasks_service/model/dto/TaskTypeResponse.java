package com.innovetsolutionstech.taskearnersng.tasks_service.model.dto;

public record TaskTypeResponse(

        String taskId,
        String taskCategory,
        String taskName,
        String taskDescription,
        String image_src,
        String priceType,
        Long taskPrice,
        String platform,
        String appName,
        String dateCreated
) {
}
