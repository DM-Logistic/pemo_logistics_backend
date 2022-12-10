package com.course.trucks.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String drivingLicense;
    private String citizenship;
    private String seriesPassportNumber;
    private String visas;
    private String role;
    private boolean isActive;
    private String avatarImageStr;
    private Set<TaskDTO> tasks = new HashSet<>();
    private CarDTO carDTO;
}
