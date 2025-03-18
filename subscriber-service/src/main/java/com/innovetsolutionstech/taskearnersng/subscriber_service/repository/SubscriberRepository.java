package com.innovetsolutionstech.taskearnersng.subscriber_service.repository;

import com.innovetsolutionstech.taskearnersng.subscriber_service.model.LoginSubscriber;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.NewSubscriber;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.NewSubscriberProfile;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.SubscriberBankDetails;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.dto.LoginResponse;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.dto.SubscriberProfileDto;

public interface SubscriberRepository<T> {
    String createSubscriber(NewSubscriber request);

    String createSubscriberProfile(NewSubscriberProfile request);

    LoginResponse authenticateUser(LoginSubscriber login);

    String resetPassword(String subscriberId);

    SubscriberProfileDto fetchSubriberProfile(String subscriberId);

    String updateAccountDetails(SubscriberBankDetails details);
}
