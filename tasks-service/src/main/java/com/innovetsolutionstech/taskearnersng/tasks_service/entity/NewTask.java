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

    @Column(name = "TASK_ID")
    private String taskId;

    @Column(name = "TASK_TYPE_ID")
    private String taskTypeID;

    @Column(name = "TASK_TYPE")
    private String taskType;

    @Column(name = "TASK_NAME")
    private String taskName;

    @Column(name = "PLATFORM")
    private String platform;

    @Column(name = "CAPTION_MESSAGE")
    private String caption_message;

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

}
