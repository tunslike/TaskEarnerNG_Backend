package com.innovetsolutionstech.taskearnersng.tasks_service.service;

import com.innovetsolutionstech.taskearnersng.tasks_service.data.NewTaskRepository;
import com.innovetsolutionstech.taskearnersng.tasks_service.data.TaskActivityRepository;
import com.innovetsolutionstech.taskearnersng.tasks_service.data.TasksDataRepository;
import com.innovetsolutionstech.taskearnersng.tasks_service.exception.TasksException;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskActivity;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskModel;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskType;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.NewTaskResponse;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskResponse;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskTypeResponse;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper.NewTaskMapper;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper.TaskActivityMapper;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper.TaskTypeMapper;
import com.innovetsolutionstech.taskearnersng.tasks_service.repository.TasksRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TasksService implements TasksRepository {

    private final TasksDataRepository tasksRepository;
    private final NewTaskRepository newTaskRepository;
    private final TaskActivityRepository taskActivityRepository;

    private final TaskTypeMapper mapper;
    private final NewTaskMapper newTaskMapper;
    private final TaskActivityMapper taskActivityMapper;

    @Override
    public String createNewTaskType(NewTaskType request) {

        try {

            var taskType = tasksRepository.save(mapper.toTaskType(request));

            return taskType.getTaskId();

        }catch (Exception e) {
            throw new TasksException("Error creating new task type" + e.getMessage());
        }
    }

    public String newTask(NewTaskModel request) {

        NewTaskResponse response;

        try {

            var newTaskID = newTaskRepository.save(newTaskMapper.toNewTask(request));

            return newTaskID.getTaskId();

        }catch (Exception e) {
            throw new TasksException("Error creating new task" + e.getMessage());
        }
    }

    @Override
    public List<TaskTypeResponse> findAllTaskTypes() {

        try {

            return tasksRepository.findAll()
                    .stream()
                    .map(mapper::fromTaskType)
                    .toList();

        }catch (Exception e) {
            throw new TasksException("Error fetching tasks" + e.getMessage());
        }
    }

    @Override
    public String submitCompletedTask(NewTaskActivity record) {
        try {

            var task = taskActivityRepository.save(taskActivityMapper.toTaskActivity(record));

            return task.getTaskId();

        }catch(Exception e) {
            throw new TasksException("Error fetching tasks" + e.getMessage());
        }
    }

    @Override
    public List<TaskResponse> findAllTaskByCompleteStatus(int completeStatus) {

        try {

            return newTaskRepository.findAllByCompleteStatus(completeStatus)
                    .stream()
                    .map(newTaskMapper::fromTasks)
                    .toList();

        }catch (Exception e) {
            throw new TasksException("Error fetching tasks" + e.getMessage());
        }
    }


}
