package com.innovetsolutionstech.taskearnersng.tasks_service.repository;

import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskActivity;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskType;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskResponse;

import java.util.List;

public interface TasksRepository {

    String createNewTaskType(NewTaskType request);

    List<TaskResponse> findAllTaskTypes();

    String submitCompletedTask(NewTaskActivity record);

}
