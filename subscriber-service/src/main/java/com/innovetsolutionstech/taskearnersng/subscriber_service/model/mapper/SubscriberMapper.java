package com.innovetsolutionstech.taskearnersng.subscriber_service.model.mapper;

import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.Subscriber;
import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.SubscriberAccess;
import com.innovetsolutionstech.taskearnersng.subscriber_service.entity.SubscriberProfile;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.NewSubscriber;
import com.innovetsolutionstech.taskearnersng.subscriber_service.model.NewSubscriberProfile;
import com.innovetsolutionstech.taskearnersng.subscriber_service.utilities.Utilities;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SubscriberMapper {
    public Subscriber toSubscriber(NewSubscriber request) {
        return Subscriber.builder()
                .subscriberId(UUID.randomUUID().toString())
                .username(request.username())
                .firstname(request.firstName())
                .lastname(request.lastName())
                .dateCreated(LocalDateTime.now())
                .authType(request.authType())
                .ipAddress(request.ipaddress())
                .build();
    }

    public SubscriberAccess toSubscriberAccess(String subscriberId, String authToken, String password) {
        return SubscriberAccess.builder()
                .accessId(UUID.randomUUID().toString())
                .subscriberId(subscriberId)
                .accessCode(Utilities.hashPINSecret(password))
                .auth_token(authToken)
                .dateCreated(LocalDateTime.now())
                .build();
    }

    public SubscriberProfile toSubscriberProfile(NewSubscriberProfile request) {
        return SubscriberProfile.builder()
                .subscriberId(request.subscriberId())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .mobileNumber(request.mobileNumber())
                .emailAddress(request.emailAddress())
                .birthday(request.birthday())
                .gender(request.gender())
                .dateCreated(LocalDateTime.now())
                .build();
    }
}
