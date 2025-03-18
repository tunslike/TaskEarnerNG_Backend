package com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.NewTask;
import com.innovetsolutionstech.taskearnersng.tasks_service.entity.TaskActivity;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskActivity;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.CompletedTaskResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskActivityMapper {

    public TaskActivity toTaskActivity(NewTaskActivity record) {
        return TaskActivity.builder()
                .taskId(record.taskID())
                .completedBy(record.taskCompletedBy())
                .socialMediaAccount(record.socialMediaAccount())
                .proofOfWork(record.proofOfWork().getOriginalFilename())
                .dateCreated(LocalDateTime.now())
                .build();
    }

    public CompletedTaskResponse loadCompletedTask(NewTask response) {
        return new CompletedTaskResponse(
                response.getTaskId(),
                response.getPlatform(),
                response.getTaskName(),
                response.getTaskType(),
                response.getTask_icon(),
                response.getPrice(),
                response.getDateCreated().toString()
        );
    }
}
