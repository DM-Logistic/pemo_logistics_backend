package com.course.trucks.dto;

import lombok.*;

@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDTO {
    private long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String password;
}
