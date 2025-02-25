package com.innovetsolutionstech.taskearnersng.tasks_service.data;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.TaskType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksDataRepository extends JpaRepository<TaskType, Integer> {


}
