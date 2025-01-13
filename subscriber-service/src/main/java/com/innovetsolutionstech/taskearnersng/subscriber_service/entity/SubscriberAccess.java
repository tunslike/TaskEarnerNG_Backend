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
@Table(name = "TENG_SUBSCRIBER_ACCESS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class SubscriberAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ_NUM")
    private Integer id;

    @Column(name = "ACCESS_ID")
    private String accessId;

    @Column(name = "SUBSCRIBER_ID")
    private String subscriberId;

    @Column(name = "ACCESS_CODE")
    private String accessCode;

    @Column(name = "AUTH_TOKEN", nullable = true)
    private String auth_token;

    @CreatedDate
    @Column(name = "DATE_CREATED", updatable = false, nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "DATE_UPDATED", updatable = false, nullable = true)
    private LocalDateTime dateUpdated;

    @Column(name = "PASSWORD_RESET_DATE", updatable = false, nullable = true)
    private LocalDateTime passwordResetDate;

}
