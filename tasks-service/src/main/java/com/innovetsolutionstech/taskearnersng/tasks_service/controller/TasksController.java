package com.innovetsolutionstech.taskearnersng.tasks_service.controller;

import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskModel;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskType;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskResponse;
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

    @GetMapping("fetchTaskTypes")
    public ResponseEntity<List<TaskResponse>> loadTasksTypes () {
        return ResponseEntity.ok(service.findAllTaskTypes());
    }

    @PostMapping("/newTask")
    public ResponseEntity<String> createNewTask (@RequestBody @Valid NewTaskModel request) {
        return ResponseEntity.ok(service.newTask(request));
    }






}
