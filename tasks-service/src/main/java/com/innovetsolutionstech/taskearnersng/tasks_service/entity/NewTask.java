package com.innovetsolutionstech.taskearnersng.tasks_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "TENG_TASKS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class NewTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ_NUM")
    private Integer id;

    @Column(name = "SUBSCRIBER_ID")
    private String subscriberId;

    @Column(name = "TASK_ID")
    private String taskId;

    @Column(name = "TASK_CATEGORY")
    private String taskCategory;

    @Column(name = "TASK_TYPE_ID")
    private String taskTypeID;

    @Column(name = "TASK_TYPE")
    private String taskType;

    @Column(name = "TASK_NAME")
    private String taskName;

    @Column(name = "PLATFORM")
    private String platform;

    @Column(name = "CAPTION_MESSAGE", nullable = true)
    private String caption_message;

    @Column(name = "ENGAGEMENT_TYPE", nullable = true)
    private String engagement_type;

    @Column(name = "SOCIAL_MEDIA_LINK", nullable = true)
    private String social_media_link;

    @Column(name = "TASK_ICON", nullable = true)
    private String task_icon;

    @Column(name = "TASK_THUMBNAIL", nullable = true)
    private String task_thumbnail;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "NO_OF_POST")
    private Integer no_Of_Post;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "LOCATION")
    private String location;

    @CreatedDate
    @Column(name = "DATE_CREATED", updatable = false, nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "PAYMENT_STATUS")
    private Integer paymentStatus;

    @Column(name = "COMPLETE_STATUS")
    private Integer completeStatus;

    @Column(name = "STATUS")
    private Integer status;

}
