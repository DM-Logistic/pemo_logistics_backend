package com.course.trucks.mapper;

import com.course.trucks.dto.TaskDTO;
import com.course.trucks.model.Task;
import com.course.trucks.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public Task mapToModel(TaskDTO taskDTO) {
        return new Task(taskDTO.getId(), taskDTO.getLoadAddress(), taskDTO.getLoadDateTime(), taskDTO.getUnloadAddress(),
                taskDTO.getUnloadDateTime(), taskDTO.getEmployee(), taskDTO.getCargoType(), taskDTO.getTaskStatus(),
                taskDTO.getDocuments(), taskDTO.getEmployeePhone(), taskDTO.getDistanceTraveled(), taskDTO.getTravelTime(),
                taskDTO.getPrice(), taskDTO.getCargoWeight(), taskDTO.getCargoVolume(), taskDTO.getComments());
    }

    public TaskDTO mapToDto(Task task) {
        List<User> users = new ArrayList<>(task.getUsers());
        TaskDTO taskDTO;
        if (users.size() == 0) {
            taskDTO = new TaskDTO(task.getId(), task.getLoadAddress(), task.getLoadDateTime(), task.getUnloadAddress(),
                    task.getUnloadDateTime(), task.getEmployee(), task.getCargoType(), task.getTaskStatus(), task.getDocuments(),
                    task.getEmployeePhone(), null, task.getDistanceTraveled(), task.getTravelTime(), task.getPrice(),
                    task.getCargoWeight(), task.getCargoVolume(), task.getComments());
        } else {
            taskDTO = new TaskDTO(task.getId(), task.getLoadAddress(), task.getLoadDateTime(), task.getUnloadAddress(),
                    task.getUnloadDateTime(), task.getEmployee(), task.getCargoType(), task.getTaskStatus(), task.getDocuments(),
                    task.getEmployeePhone(), users.get(0).getId(), task.getDistanceTraveled(), task.getTravelTime(), task.getPrice(),
                    task.getCargoWeight(), task.getCargoVolume(), task.getComments());
        }
        return taskDTO;
    }

}
