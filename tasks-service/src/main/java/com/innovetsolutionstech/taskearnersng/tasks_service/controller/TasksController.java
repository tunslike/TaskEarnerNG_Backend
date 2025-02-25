package com.innovetsolutionstech.taskearnersng.tasks_service.controller;

import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskActivity;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskModel;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskType;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.NewTaskResponse;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskResponse;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskTypeResponse;
import com.innovetsolutionstech.taskearnersng.tasks_service.service.TasksService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TasksController {

    private final TasksService service;

    @PostMapping("/createTaskType")
    public ResponseEntity<String> createNewTaskType (@RequestBody @Valid NewTaskType request) {
        return ResponseEntity.ok(service.createNewTaskType(request));
    }

    @PostMapping("/submitTask")
    public ResponseEntity<String> submitCompletedTask (@RequestBody @Valid NewTaskActivity request) {
        return ResponseEntity.ok(service.submitCompletedTask(request));
    }

    @GetMapping("fetchTaskTypes")
    public ResponseEntity<List<TaskTypeResponse>> loadAllTasks () {
        return ResponseEntity.ok(service.findAllTaskTypes());
    }

    @GetMapping("fetchAllTasks")
    public ResponseEntity<List<TaskResponse>> loadTasksTypes (@RequestParam int status) {
        return ResponseEntity.ok(service.findAllTaskByCompleteStatus(status));
    }

    @PostMapping("/newTask")
    public ResponseEntity<NewTaskResponse> createNewTask (@RequestBody @Valid NewTaskModel request) {
        NewTaskResponse response = new NewTaskResponse(
                service.newTask(request),200,"New task created successfully"
        );
        return ResponseEntity.ok(response);
    }






}
