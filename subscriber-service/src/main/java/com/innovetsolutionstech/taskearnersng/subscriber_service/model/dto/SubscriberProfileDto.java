package com.innovetsolutionstech.taskearnersng.subscriber_service.model.dto;

import jakarta.persistence.Column;

public record SubscriberProfileDto(
        String subscriberId,
        String firstName,
        String lastName,
        String mobileNumber,
        String emailAddress,
        String gender,
        String birthday,
        String religion,
        String location,
        String bankName,
        String accountNumber,
        String accountName
) {
}
