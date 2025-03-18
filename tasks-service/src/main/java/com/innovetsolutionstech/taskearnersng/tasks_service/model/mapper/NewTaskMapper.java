package com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.NewTask;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskModel;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.SubscribedTaskDto;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class NewTaskMapper {


    // save new tasks
    public NewTask toNewTask (NewTaskModel record) {
        return NewTask.builder()
                .subscriberId(record.subscriberID())
                .taskId(UUID.randomUUID().toString())
                .taskName(record.taskName())
                .taskTypeID(record.taskTypeID())
                .taskType(record.taskType())
                .caption_message(record.caption_message())
                .engagement_type(record.engagement_type())
                .social_media_link(record.social_media_link())
                .price(record.price())
                .noOfPost(record.no_of_post())
                .platform(record.platform())
                .location(record.location())
                .gender(record.gender())
                .dateCreated(LocalDateTime.now())
                .build();
    }

    // fetch all tasks
    public TaskResponse fromTasks (NewTask task) {
        return new TaskResponse(
                task.getTaskId(),
                task.getTaskCategory(),
                task.getTaskType(),
                task.getTaskTypeID(),
                task.getTaskName(),
                task.getPlatform(),
                task.getPrice(),
                task.getCaption_message(),
                task.getTask_icon(),
                task.getTask_thumbnail(),
                task.getDateCreated().toString()
        );
    }

    //fetch all subcriber tasks
    public SubscribedTaskDto loadSubscribedTask(NewTask request) {
        return new SubscribedTaskDto(
                request.getTaskId(),
                request.getPlatform(),
                request.getTaskName(),
                request.getTask_icon(),
                request.getTaskType(),
                request.getPrice(),
                request.getNoOfPost(),
                request.getDateCreated().toString(),
                request.getPaymentStatus(),
                request.getStatus()
        );
    }
}
