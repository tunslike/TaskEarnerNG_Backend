package com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.NewTask;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class NewTaskMapper {

    public NewTask toNewTask (NewTaskModel record) {
        return NewTask.builder()
                .taskId(UUID.randomUUID().toString())
                .taskName(record.taskName())
                .taskTypeID(record.taskTypeID())
                .taskType(record.taskType())
                .caption_message(record.caption_message())
                .engagement_type(record.engagement_type())
                .social_media_link(record.social_media_link())
                .price(record.price())
                .no_Of_Post(record.no_of_post())
                .platform(record.platform())
                .location(record.location())
                .gender(record.gender())
                .dateCreated(LocalDateTime.now())
                .build();
    }
}
