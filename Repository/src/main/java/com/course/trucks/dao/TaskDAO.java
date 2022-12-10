package com.course.trucks.dao;

import com.course.trucks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDAO extends JpaRepository<Task, Long> {

    void deleteById(long id);

    Task findFirstByOrderByIdDesc();

    @Modifying
    @Query(value = "DELETE FROM users_tasks WHERE task_id = :taskId", nativeQuery = true)
    void deleteTaskFromLinkTableById(@Param("taskId") long taskId);

    @Query(value = "SELECT LAST_INSERT_ID() FROM tasks", nativeQuery = true)
    long getLastInsertId();

}
