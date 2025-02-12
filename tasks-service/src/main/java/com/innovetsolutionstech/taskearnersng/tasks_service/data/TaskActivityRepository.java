package com.innovetsolutionstech.taskearnersng.tasks_service.data;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.TaskActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskActivityRepository extends JpaRepository<TaskActivity, Integer> {

}
