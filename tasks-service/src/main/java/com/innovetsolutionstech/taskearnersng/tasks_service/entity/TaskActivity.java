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
@Table(name = "TENG_TASK_ACTIVITY")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class TaskActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ_NUM")
    private Integer id;

    @Column(name = "TASK_ID")
    private String taskId;

    @Column(name = "COMPLETED_BY")
    private String completedBy;

    @Column(name = "SOCIAL_MEDIA_ACCOUNT")
    private String socialMediaAccount;

    @Column(name = "PROOF_OF_WORK")
    private String proofOfWork;

    @CreatedDate
    @Column(name = "DATE_CREATED", updatable = false, nullable = false)
    private LocalDateTime dateCreated;

}
