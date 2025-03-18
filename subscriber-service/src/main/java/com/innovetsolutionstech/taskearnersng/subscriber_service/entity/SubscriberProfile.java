package com.innovetsolutionstech.taskearnersng.subscriber_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "TENG_SUBSCRIBER_PROFILE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class SubscriberProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ_NUM")
    private Integer id;

    @Column(name = "SUBSCRIBER_ID")
    private String subscriberId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "BIRTHDAY")
    private String birthday;

    @Column(name = "RELIGION")
    private String religion;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "BANK_NAME")
    private String bank_name;

    @Column(name = "ACCOUNT_NUMBER")
    private String account_number;

    @Column(name = "ACCOUNT_NAME")
    private String account_name;

    @CreatedDate
    @Column(name = "DATE_CREATED", updatable = false, nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "DATE_UPDATED", updatable = false, nullable = true)
    private LocalDateTime dateUpdated;

    @Column(name = "ACCOUNT_DATE_UPDATE", updatable = false, nullable = true)
    private LocalDateTime accountDateUpdate;

}
