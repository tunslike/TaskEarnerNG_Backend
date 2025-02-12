package com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.TaskActivity;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskActivity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskActivityMapper {

    public TaskActivity toTaskActivity(NewTaskActivity record) {
        return TaskActivity.builder()
                .taskId(record.taskID())
                .completedBy(record.taskCompletedBy())
                .socialMediaAccount(record.socialMediaAccount())
                .proofOfWork(record.proofOfWork())
                .dateCreated(LocalDateTime.now())
                .build();
    }
}
