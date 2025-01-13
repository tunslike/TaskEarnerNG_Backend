package com.innovetsolutionstech.taskearnersng.subscriber_service.entity;

import com.innovetsolutionstech.taskearnersng.subscriber_service.enums.AuthType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "TENG_SUBSCRIBER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ_NUM")
    private Integer id;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "SUBSCRIBER_ID")
    private String subscriberId;

    @Column(name = "FIRST_NAME")
    private String firstname;

    @Column(name = "LAST_NAME")
    private String lastname;

    @Column(name = "AUTH_TYPE")
    @Enumerated(EnumType.STRING)
    private AuthType authType;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @CreatedDate
    @Column(name = "DATE_CREATED", updatable = false, nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "FIRST_LOGIN_DATE", updatable = true, nullable = true)
    private LocalDateTime firstLoginDate;

    @Column(name = "LAST_LOGIN_DATE", updatable = true, nullable = true)
    private LocalDateTime lastLoginDate;

}
