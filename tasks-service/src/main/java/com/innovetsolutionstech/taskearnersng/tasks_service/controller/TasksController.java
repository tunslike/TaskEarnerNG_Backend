package com.innovetsolutionstech.taskearnersng.tasks_service.controller;

import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskActivity;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskModel;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskType;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.TaskSessionModel;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.*;
import com.innovetsolutionstech.taskearnersng.tasks_service.service.TasksService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<String> submitCompletedTask (
            @RequestParam("file")MultipartFile file,
            @RequestParam(value = "taskId", required = true) String taskId,
            @RequestParam(value = "socialMediaAccount", required = true) String socialMediaAccount,
            @RequestParam(value = "taskCompletedBy", required = true) String taskCompletedBy
            ) {

        if(file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("File is required.");
        }

        NewTaskActivity request = new NewTaskActivity(
                taskId,
                taskCompletedBy,
                socialMediaAccount, file
        );

        return ResponseEntity.ok(service.submitCompletedTask(request));
    }

    @GetMapping("fetchTaskTypes")
    public ResponseEntity<List<TaskTypeResponse>> loadAllTasks () {
        return ResponseEntity.ok(service.findAllTaskTypes());
    }

    @PostMapping("saveTaskSession")
    public ResponseEntity<String> saveTaskSession(@RequestBody @Valid TaskSessionModel session) {
        return ResponseEntity.ok(service.saveTaskSession(session));
    }

    @GetMapping("fetchAllTasks")
    public ResponseEntity<List<TaskResponse>> loadTasksTypes (@RequestParam int status) {
        return ResponseEntity.ok(service.findAllTaskByCompleteStatus(status));
    }

    @GetMapping("fetchSearchTasks")
    public ResponseEntity<List<TaskResponse>> fetchSearchTasks (@RequestParam(required = false) String searchText)  {

        if(searchText == null || searchText.isEmpty()) {

            int defaultStatus = 0;

            return ResponseEntity.ok(service.findAllTaskByCompleteStatus(defaultStatus));
        }
        return ResponseEntity.ok(service.loadSearchTasks(searchText));
    }

    @GetMapping("loadCompletedTasks")
    public ResponseEntity<List<CompletedTaskResponse>> loadSubscriberTasks(@RequestParam String subscriberId) {
        return ResponseEntity.ok(service.loadCompletedTask(subscriberId));
    }

    @GetMapping("loadSubscribedTasks")
    public ResponseEntity<List<SubscribedTaskDto>> loadSubscribedTasks(@RequestParam String subscriberId) {
        return ResponseEntity.ok(service.loadSubscribedTasks(subscriberId));
    }

    @GetMapping("loadTaskSession")
    public ResponseEntity<List<TaskResponse>> loadTaskSession (@RequestParam String subscriberId) {
        return ResponseEntity.ok(service.loadTaskSession(subscriberId));
    }

    @PostMapping("/newTask")
    public ResponseEntity<NewTaskResponse> createNewTask (@RequestBody @Valid NewTaskModel request) {
        NewTaskResponse response = new NewTaskResponse(
                service.newTask(request),200,"New task created successfully"
        );
        return ResponseEntity.ok(response);
    }






}
