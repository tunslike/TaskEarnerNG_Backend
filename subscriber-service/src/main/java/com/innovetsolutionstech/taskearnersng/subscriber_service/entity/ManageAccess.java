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
@Table(name = "TENG_MANAGE_ACCESS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ManageAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ_NUM")
    private Integer id;

    @Column(name = "TOKEN_ID")
    private String token_id;

    @Column(name = "REFRESH_TOKEN")
    private String token;

    @Column(name = "SUBSCRIBER_ID")
    private String subscriberId;

    @CreatedDate
    @Column(name = "DATE_CREATED", updatable = false, nullable = false)
    private LocalDateTime dateCreated;

    @CreatedDate
    @Column(name = "EXPIRY_DATE", updatable = false, nullable = false)
    private LocalDateTime expiryDate;
}
