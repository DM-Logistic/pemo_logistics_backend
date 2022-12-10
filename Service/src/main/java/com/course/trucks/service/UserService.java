package com.course.trucks.service;

import com.course.trucks.dto.AuthUserDTO;
import com.course.trucks.dto.RoleUserDTO;
import com.course.trucks.dto.TaskDTO;
import com.course.trucks.dto.UserDTO;
import com.course.trucks.exception.ServiceException;

import java.util.List;
import java.util.Set;

public interface UserService {

    /**
     * save user to database
     *
     * @param userDTO is the data transfer object for model user
     */
    void createUser(UserDTO userDTO);

    /**
     * updates user if it exists or save user if not
     *
     * @param userDTO is the data transfer object for model user
     */
    void updateUser(UserDTO userDTO);

    /**
     * gets list of all users in database
     *
     * @return list of users
     */
    List<UserDTO> getUsers();

    /**
     * gets user by its id
     *
     * @param id is the user id
     * @return user with appropriate id
     */
    UserDTO getUserById(long id);

    /**
     * deletes user by id
     *
     * @param id is the id of user that wished to be deleted
     */
    void deleteUser(long id);

    /**
     * gets tasks by user id
     *
     * @param userId is the id of user
     * @return set of tasks that are belonged to user with appropriate id
     */
    Set<TaskDTO> getTasksByUserId(long userId);

    /**
     * assigns task to user
     *
     * @param userId is the user id
     * @param taskId is the task id
     */
    void assignTaskToUser(long userId, long taskId);

    /**
     * gets user with role fields by email
     *
     * @param email is the user email
     * @return user that has given email
     */
    RoleUserDTO getRoleUserByEmail(String email);

    /**
     * gets user by email
     *
     * @param email is the user email
     * @return user that has given email
     */
    UserDTO getUserByEmail(String email);

    /**
     * save new user to database
     *
     * @param authUserDTO data transfer object that has all needed fields for registration
     * @throws ServiceException is the module exception
     */
    void register(AuthUserDTO authUserDTO) throws ServiceException;

    /**
     * updates user status(field isActive in database)
     *
     * @param userId   is the user id
     * @param isActive is the new value of field isActive
     */
    void updateIsActiveById(long userId, boolean isActive);

}
