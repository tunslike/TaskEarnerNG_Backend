package com.innovetsolutionstech.taskearnersng.tasks_service.data;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.NewTask;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.NewTaskResponse;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NewTaskRepository extends JpaRepository<NewTask, Integer> {

    //select all task where completed_status = 0
    List<NewTask> findAllByCompleteStatus(int completedStatus);

    // find by No of post
    @Query("SELECT t.noOfPost FROM NewTask t WHERE t.taskId = :taskId")
    Integer findNoOfPostByTaskId(@Param("taskId") String taskId);

    // update task with completed status
    @Modifying
    @Transactional
    @Query("UPDATE NewTask t SET t.completeStatus = :status, t.dateCompleted = :dateCompleted WHERE t.taskId = :taskId")
    int updateTaskCompletedAndDate(String taskId, int status, LocalDateTime dateCompleted);

    // load task session by subscriber id
    @Query(value = "SELECT T.* FROM TENG_TASKS T LEFT JOIN TENG_TASKS_SESSION S ON T.TASK_ID = S.TASK_ID WHERE " +
            "S.SUBSCRIBER_ID = :subscriberId AND S.TASK_ID NOT IN (SELECT TASK_ID FROM TENG_TASK_ACTIVITY WHERE " +
            "STATUS = 0 AND COMPLETED_BY = :subscriberId)", nativeQuery = true)
    List<NewTask> findTaskSessionBySubscriberId(@Param("subscriberId") String subscriberId);

    @Query(value = "SELECT T.* FROM TENG_TASKS T LEFT JOIN TENG_TASK_ACTIVITY A ON T.TASK_ID = A.TASK_ID " +
            "WHERE A.COMPLETED_BY = :subscriberId", nativeQuery = true)
    List<NewTask> loadCompletedTaskBySubscriber(@Param("subscriberId") String subscriberId);

    // load subscribed tasks
    List<NewTask> findBySubscriberId(@Param("subscriberId") String subscriberId);

    // search query
    @Query("SELECT t FROM NewTask t WHERE LOWER(t.taskName) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<NewTask> fetchSearchTasks(@Param("search") String search);

}
