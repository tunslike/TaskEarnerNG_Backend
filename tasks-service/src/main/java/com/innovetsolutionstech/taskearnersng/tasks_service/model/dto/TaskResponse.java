package com.innovetsolutionstech.taskearnersng.tasks_service.model.dto;

import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public record TaskResponse(

        String taskId,
        String taskName,
        String taskDescription,
        String priceType,
        Long taskPrice,
        String platform,
        String appName,
        String dateCreated
) {
}
