package com.innovetsolutionstech.taskearnersng.tasks_service.repository;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.NewTask;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskActivity;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskType;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.TaskSessionModel;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.CompletedTaskResponse;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.SubscribedTaskDto;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskResponse;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskTypeResponse;

import java.util.List;

public interface TasksRepository {

    String createNewTaskType(NewTaskType request);

    List<TaskTypeResponse> findAllTaskTypes();

    String submitCompletedTask(NewTaskActivity record);

    List<TaskResponse> findAllTaskByCompleteStatus(int completeStatus);

    List<TaskResponse> loadTaskSession(String subscriberId);

    String saveTaskSession(TaskSessionModel session);

    List<TaskResponse> loadSearchTasks(String searchText);

    List<CompletedTaskResponse> loadCompletedTask(String subscriberId);

    List<SubscribedTaskDto> loadSubscribedTasks(String subscriberId);

}
