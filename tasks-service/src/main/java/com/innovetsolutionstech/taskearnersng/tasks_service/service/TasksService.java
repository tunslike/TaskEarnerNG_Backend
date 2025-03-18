package com.innovetsolutionstech.taskearnersng.tasks_service.service;

import com.innovetsolutionstech.taskearnersng.tasks_service.data.NewTaskRepository;
import com.innovetsolutionstech.taskearnersng.tasks_service.data.TaskActivityRepository;
import com.innovetsolutionstech.taskearnersng.tasks_service.data.TaskSessionRepository;
import com.innovetsolutionstech.taskearnersng.tasks_service.data.TasksDataRepository;
import com.innovetsolutionstech.taskearnersng.tasks_service.entity.NewTask;
import com.innovetsolutionstech.taskearnersng.tasks_service.entity.TaskSession;
import com.innovetsolutionstech.taskearnersng.tasks_service.exception.TasksException;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskActivity;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskModel;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskType;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.TaskSessionModel;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.*;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper.NewTaskMapper;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper.TaskActivityMapper;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper.TaskSessionMapper;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper.TaskTypeMapper;
import com.innovetsolutionstech.taskearnersng.tasks_service.repository.TasksRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class TasksService implements TasksRepository {

    private final TasksDataRepository tasksRepository;
    private final NewTaskRepository newTaskRepository;
    private final TaskActivityRepository taskActivityRepository;
    private final TaskSessionRepository taskSessionRepository;

    private final TaskTypeMapper mapper;
    private final NewTaskMapper newTaskMapper;
    private final TaskActivityMapper taskActivityMapper;
    private  final TaskSessionMapper taskSessionMapper;

    private static final String UPLOAD_DIR = "uploads/proofOfWork/";

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

            //check target no of post
            long taskNoOfPost = getTaskTotalPost(record.taskID());

            //get total completed task
            long getTaskCount = getTotalCompleteTask(record.taskID());

            //check
            if(taskNoOfPost == getTaskCount) {
                return record.taskID();
            }

            MultipartFile file = record.proofOfWork();
            boolean fileDir = false;

            File uploadDirectory = new File(UPLOAD_DIR);
            if(!uploadDirectory.exists()) {
                fileDir = uploadDirectory.mkdirs();
            }

            // save file to directory
            String filePath = UPLOAD_DIR + file.getOriginalFilename();
            Path destinationPath = Paths.get(filePath);
            Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            var task = taskActivityRepository.save(taskActivityMapper.toTaskActivity(record));

            long verifyTaskCount = getTotalCompleteTask(record.taskID());

            if(taskNoOfPost == verifyTaskCount) {

                // close tasks and set to completed
                closeCompletedTask(record.taskID());

            }

            return task.getTaskId();

        }catch(Exception e) {
            throw new TasksException("Error submitting tasks: " + e.getMessage());
        }
    }

    private void closeCompletedTask(String taskId) {
        try {

            // update status
            int taskStatus = newTaskRepository.updateTaskCompletedAndDate(taskId, 1, LocalDateTime.now());

        }catch (Exception e) {
            throw new TasksException("Error fetching : " + e.getMessage());
        }
    }

    // get total number of post for task
    private int getTaskTotalPost (String taskId) {
        try {

            return newTaskRepository.findNoOfPostByTaskId(taskId);

        }catch (Exception e) {
            throw new TasksException("Error fetching : " + e.getMessage());
        }
    }

    // return total number of completed tasks
    private long getTotalCompleteTask(String taskId) {
        try {

            return taskActivityRepository.countByTaskId(taskId);

        }catch (Exception e) {
            throw new TasksException("Error fetching : " + e.getMessage());
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
            throw new TasksException("Error fetching tasks: " + e.getMessage());
        }
    }

    @Override
    public List<TaskResponse> loadSearchTasks(String searchText) {

        try {

            return newTaskRepository.fetchSearchTasks(searchText)
                    .stream()
                    .map(newTaskMapper::fromTasks)
                    .toList();

        }catch (Exception e) {
            throw new TasksException("Error fetching tasks: " + e.getMessage());
        }
    }

    @Override
    public List<CompletedTaskResponse> loadCompletedTask(String subscriberId) {

        try {

            return newTaskRepository.loadCompletedTaskBySubscriber(subscriberId)
                    .stream()
                    .map(taskActivityMapper::loadCompletedTask)
                    .toList();

        }catch (Exception e) {
            throw new TasksException("Error loading completed task : " + e.getMessage());
        }
    }

    @Override
    public List<SubscribedTaskDto> loadSubscribedTasks(String subscriberId) {
        try {

            return newTaskRepository.findBySubscriberId(subscriberId)
                    .stream()
                    .map(newTaskMapper::loadSubscribedTask)
                    .toList();

        }catch (Exception e) {
            throw new TasksException("Error loading completed task : " + e.getMessage());
        }
    }

    @Override
    public List<TaskResponse> loadTaskSession(String subscriberId) {
        try {
            return newTaskRepository.findTaskSessionBySubscriberId(subscriberId)
                    .stream()
                    .map(newTaskMapper::fromTasks)
                    .toList();
        }catch (Exception e) {
            throw new TasksException("Error fetching saved tasks for subscriber: [" + subscriberId + "]" +  e.getMessage());
        }
    }



    @Override
    public String saveTaskSession(TaskSessionModel session) {

        try {

            Optional<TaskSession> existingSession = taskSessionRepository.findTaskSessionByTaskIdAndSubscriber(session.taskID(), session.subscriberId());

            if(existingSession.isPresent()) {
                return existingSession.get().getSessionId();

            }else {

                var sessionId = taskSessionRepository.save(taskSessionMapper.SaveSession(session));

                return sessionId.getSessionId();
            }
        }catch (Exception e) {
            throw new TasksException("Error saving task session: " + e.getMessage());
        }

    }


}
