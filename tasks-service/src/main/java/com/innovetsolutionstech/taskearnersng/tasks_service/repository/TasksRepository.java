package com.innovetsolutionstech.taskearnersng.tasks_service.repository;

import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskActivity;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskType;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.NewTaskResponse;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskResponse;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskTypeResponse;

import java.util.List;

public interface TasksRepository {

    String createNewTaskType(NewTaskType request);

    List<TaskTypeResponse> findAllTaskTypes();

    String submitCompletedTask(NewTaskActivity record);

    List<TaskResponse> findAllTaskByCompleteStatus(int completeStatus);

}
