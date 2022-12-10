package com.course.trucks.dto;

import lombok.*;

@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private long id;
    private String model;
    private String number;
    private String mark;
    private String trailerType;
    private String loadCapacity;
    private String volume;
}
