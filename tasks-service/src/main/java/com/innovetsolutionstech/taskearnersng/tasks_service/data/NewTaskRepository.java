package com.innovetsolutionstech.taskearnersng.tasks_service.data;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.NewTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewTaskRepository extends JpaRepository<NewTask, Integer> {
}
