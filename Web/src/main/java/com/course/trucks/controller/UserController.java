package com.course.trucks.controller;

import com.course.trucks.dto.TaskDTO;
import com.course.trucks.dto.UserDTO;
import com.course.trucks.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@RequestBody UserDTO userDTO) {
        LOGGER.info("Creating user...");
        userService.createUser(userDTO);
        LOGGER.info("User was created.");
    }

    @PostMapping(value = "/{userId}/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void assignTaskToUser(@PathVariable long userId, @PathVariable long taskId) {
        LOGGER.info("Assigning task to user...");
        userService.assignTaskToUser(userId, taskId);
        LOGGER.info("Task was assigned.");
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody UserDTO userDTO, @PathVariable long id) {
        LOGGER.info("Updating user...");
        userDTO.setId(id);
        userService.updateUser(userDTO);
        LOGGER.info("User was updated.");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getUsers() {
        LOGGER.info("Getting users.");
        return userService.getUsers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable long id) {
        LOGGER.info("Getting user by id");
        return userService.getUserById(id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable long id) {
        LOGGER.info("Deleting user...");
        userService.deleteUser(id);
        LOGGER.info("User was deleted.");
    }

    @GetMapping(value = "/{userId}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Set<TaskDTO> getTasksByUserId(@PathVariable long userId) {
        LOGGER.info("Getting tasks by user id.");
        return userService.getTasksByUserId(userId);
    }

    @PatchMapping(value = "/{userId}/{isActive}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateUserIsActiveById(@PathVariable long userId, @PathVariable boolean isActive) {
        LOGGER.info("Updating user status(isActive)...");
        userService.updateIsActiveById(userId, isActive);
        LOGGER.info("Updated.");
    }

}
