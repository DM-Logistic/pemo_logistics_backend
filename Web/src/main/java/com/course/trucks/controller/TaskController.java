package com.course.trucks.controller;

import com.course.trucks.dto.IdDTO;
import com.course.trucks.dto.TaskDTO;
import com.course.trucks.service.TaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {

    private final static Logger LOGGER = Logger.getLogger(TaskController.class);

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public IdDTO createTask(@RequestBody TaskDTO taskDTO) {
        LOGGER.info("Creating task...");
        long lastInsertId = taskService.createTask(taskDTO);
        LOGGER.info("Task was created.");
        return new IdDTO(lastInsertId);
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateTask(@RequestBody TaskDTO taskDTO, @PathVariable long id) {
        LOGGER.info("Updating task...");
        taskDTO.setId(id);
        taskService.updateTask(taskDTO);
        LOGGER.info("Task was updated.");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> getTasks() {
        LOGGER.info("Get tasks list.");
        return taskService.getTasks();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO getTaskById(@PathVariable long id) {
        LOGGER.info("Get task by id.");
        return taskService.getTaskById(id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable long id) {
        LOGGER.info("Deleting task...");
        taskService.deleteTask(id);
        LOGGER.info("Task was deleted");
    }

}
