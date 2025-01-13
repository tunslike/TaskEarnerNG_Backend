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
@Table(name = "TENG_TASKS_TYPE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class TaskType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ_NUM")
    private Integer id;

    @Column(name = "TASK_ID")
    private String taskId;

    @Column(name = "TASK_NAME")
    private String taskName;

    @Column(name = "TASK_DESCRIPTION")
    private String taskDescription;

    @Column(name = "PRICE_TYPE")
    private String priceType;

    @Column(name = "TASK_PRICE")
    private Long taskPrice;

    @Column(name = "PLATFORM")
    private String platform;

    @Column(name = "APP_NAME")
    private String appName;

    @CreatedDate
    @Column(name = "DATE_CREATED", updatable = false, nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "DATE_UPDATED", updatable = false, nullable = true)
    private LocalDateTime dateUpdated;

}
