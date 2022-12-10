package com.course.trucks.service;

import com.course.trucks.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    /**
     * saves task to database
     *
     * @param taskDTO is the data transfer object for model task
     * @return id of task that was created just now
     */
    long createTask(TaskDTO taskDTO);

    /**
     * updates task if it exists or save task if not
     *
     * @param taskDTO is the data transfer object for model task
     */
    void updateTask(TaskDTO taskDTO);

    /**
     * gets list of all tasks existing in database
     *
     * @return list of tasks
     */
    List<TaskDTO> getTasks();

    /**
     * delete task from database by its id
     *
     * @param id is the id of task that wished to be deleted
     */
    void deleteTask(long id);

    /**
     * gets task by its id
     *
     * @param id is the id of task that wished to be got
     * @return gets task corresponding to given id
     */
    TaskDTO getTaskById(long id);

}
