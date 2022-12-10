package com.course.trucks.service.impl;

import com.course.trucks.dao.TaskDAO;
import com.course.trucks.dao.UserDAO;
import com.course.trucks.dto.TaskDTO;
import com.course.trucks.mapper.TaskMapper;
import com.course.trucks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private static final String IN_PROGRESS = "Выполняется";
    private static final String READY = "Выполнена";

    private final TaskMapper taskMapper;
    private final TaskDAO taskDAO;
    private final UserDAO userDAO;

    @Autowired
    public TaskServiceImpl(TaskMapper taskMapper, TaskDAO taskDAO, UserDAO userDAO) {
        this.taskMapper = taskMapper;
        this.taskDAO = taskDAO;
        this.userDAO = userDAO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long createTask(TaskDTO taskDTO) {
        taskDAO.save(taskMapper.mapToModel(taskDTO));
        return taskDAO.findFirstByOrderByIdDesc().getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTask(TaskDTO taskDTO) {
        Long userId = userDAO.getUserIdByTaskId(taskDTO.getId());
        if (Objects.nonNull(userId)) {
            if (taskDTO.getTaskStatus().equals(IN_PROGRESS)) {
                userDAO.makeUserActiveById(userId);
            } else if (taskDTO.getTaskStatus().equals(READY)) {
                userDAO.makeUserNonActiveById(userId);
            }
        }
        taskDAO.save(taskMapper.mapToModel(taskDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<TaskDTO> getTasks() {
        return taskDAO.findAll().stream().map(taskMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTask(long id) {
        taskDAO.deleteTaskFromLinkTableById(id);
        taskDAO.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TaskDTO getTaskById(long id) {
        return taskMapper.mapToDto(taskDAO.getById(id));
    }
}
