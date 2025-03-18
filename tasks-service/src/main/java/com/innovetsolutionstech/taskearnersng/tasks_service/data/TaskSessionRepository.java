package com.innovetsolutionstech.taskearnersng.tasks_service.data;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.TaskSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaskSessionRepository extends JpaRepository<TaskSession, Integer> {

    @Query(value = "SELECT t FROM TaskSession t WHERE t.taskId = :taskId AND t.subscriberId = :subscriberId")
    Optional<TaskSession> findTaskSessionByTaskIdAndSubscriber(String taskId, String subscriberId);
}
