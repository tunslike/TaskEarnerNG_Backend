package com.innovetsolutionstech.taskearnersng.tasks_service.data;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.NewTask;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.NewTaskResponse;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewTaskRepository extends JpaRepository<NewTask, Integer> {

    //select all task where completed_status = 0
    List<NewTask> findAllByCompleteStatus(int completedStatus);
}
