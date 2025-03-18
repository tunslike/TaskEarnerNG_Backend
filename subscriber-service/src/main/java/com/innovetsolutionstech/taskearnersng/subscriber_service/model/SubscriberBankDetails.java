package com.innovetsolutionstech.taskearnersng.subscriber_service.model;

public record SubscriberBankDetails(
        String subscriberId,
        String bankName,
        String accountName,
        String accountNumber
) {
}
