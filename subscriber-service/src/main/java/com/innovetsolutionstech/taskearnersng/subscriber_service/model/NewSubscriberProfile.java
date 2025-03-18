package com.innovetsolutionstech.taskearnersng.subscriber_service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record NewSubscriberProfile(

        @NotNull(message = "Subscriber Id is required")
        String subscriberId,

        @NotNull(message = "Lastname is required")
        String lastName,

        @NotNull(message = "Firstname is required")
        String firstName,

        @NotNull(message = "Mobile Number is required")
        String mobileNumber,

        @NotNull(message = "Gender is required")
        String gender,

        @NotNull(message = "Email address is required")
        @Email(message = "Email must be valid email")
        String emailAddress,

        @NotNull
        String birthday,

        String religion,

        String location

) {
}
