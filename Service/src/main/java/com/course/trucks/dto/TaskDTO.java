package com.course.trucks.dto;

import lombok.*;

@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
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
    private Long driverId;
    private String distanceTraveled;
    private String travelTime;
    private String price;
    private String cargoWeight;
    private String cargoVolume;
    private String comments;
}
