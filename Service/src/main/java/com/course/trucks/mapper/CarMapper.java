package com.course.trucks.mapper;

import com.course.trucks.dto.CarDTO;
import com.course.trucks.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public Car mapToModel(CarDTO carDTO) {
        return new Car(carDTO.getId(), carDTO.getModel(), carDTO.getNumber(), carDTO.getMark(), carDTO.getTrailerType(),
                carDTO.getLoadCapacity(), carDTO.getVolume());
    }

    public CarDTO mapToDto(Car car) {
        return new CarDTO(car.getId(), car.getModel(), car.getNumber(), car.getMark(), car.getTrailerType(),
                car.getLoadCapacity(), car.getVolume());
    }

}
