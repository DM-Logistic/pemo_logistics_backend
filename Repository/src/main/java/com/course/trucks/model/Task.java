package com.course.trucks.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String loadAddress;
    private String loadDateTime;
    private String unloadAddress;
    private String unloadDateTime;
    private String employee;
    private String cargoType;
    private String taskStatus;
    private String documents;
    private String employeePhone;
    private String distanceTraveled;
    private String travelTime;
    private String price;
    private String cargoWeight;
    private String cargoVolume;
    private String comments;
    @ManyToMany(mappedBy = "tasks")
    private Set<User> users = new HashSet<>();

    public Task(long id, String loadAddress, String loadDateTime, String unloadAddress, String unloadDateTime, String employee, String cargoType, String taskStatus, String documents, String employeePhone, String distanceTraveled, String travelTime, String price, String cargoWeight, String cargoVolume, String comments) {
        this.id = id;
        this.loadAddress = loadAddress;
        this.loadDateTime = loadDateTime;
        this.unloadAddress = unloadAddress;
        this.unloadDateTime = unloadDateTime;
        this.employee = employee;
        this.cargoType = cargoType;
        this.taskStatus = taskStatus;
        this.documents = documents;
        this.employeePhone = employeePhone;
        this.distanceTraveled = distanceTraveled;
        this.travelTime = travelTime;
        this.price = price;
        this.cargoWeight = cargoWeight;
        this.cargoVolume = cargoVolume;
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (!Objects.equals(loadAddress, task.loadAddress)) return false;
        if (!Objects.equals(loadDateTime, task.loadDateTime)) return false;
        if (!Objects.equals(unloadAddress, task.unloadAddress))
            return false;
        if (!Objects.equals(unloadDateTime, task.unloadDateTime))
            return false;
        if (!Objects.equals(employee, task.employee)) return false;
        if (!Objects.equals(cargoType, task.cargoType)) return false;
        if (!Objects.equals(taskStatus, task.taskStatus)) return false;
        if (!Objects.equals(documents, task.documents)) return false;
        if (!Objects.equals(employeePhone, task.employeePhone))
            return false;
        if (!Objects.equals(distanceTraveled, task.distanceTraveled))
            return false;
        if (!Objects.equals(travelTime, task.travelTime)) return false;
        if (!Objects.equals(price, task.price)) return false;
        if (!Objects.equals(cargoWeight, task.cargoWeight)) return false;
        if (!Objects.equals(cargoVolume, task.cargoVolume)) return false;
        return Objects.equals(comments, task.comments);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (loadAddress != null ? loadAddress.hashCode() : 0);
        result = 31 * result + (loadDateTime != null ? loadDateTime.hashCode() : 0);
        result = 31 * result + (unloadAddress != null ? unloadAddress.hashCode() : 0);
        result = 31 * result + (unloadDateTime != null ? unloadDateTime.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (cargoType != null ? cargoType.hashCode() : 0);
        result = 31 * result + (taskStatus != null ? taskStatus.hashCode() : 0);
        result = 31 * result + (documents != null ? documents.hashCode() : 0);
        result = 31 * result + (employeePhone != null ? employeePhone.hashCode() : 0);
        result = 31 * result + (distanceTraveled != null ? distanceTraveled.hashCode() : 0);
        result = 31 * result + (travelTime != null ? travelTime.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (cargoWeight != null ? cargoWeight.hashCode() : 0);
        result = 31 * result + (cargoVolume != null ? cargoVolume.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }

}
