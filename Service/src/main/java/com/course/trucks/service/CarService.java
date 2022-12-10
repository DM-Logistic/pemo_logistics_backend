package com.course.trucks.service;

import com.course.trucks.dto.CarDTO;

public interface CarService {

    /**
     * saves car to database
     *
     * @param carDTO is the data transfer object for model car
     */
    void insertCar(CarDTO carDTO);

    /**
     * updates car if it exists or save car if not
     *
     * @param carDTO is the data transfer object for model car
     */
    void updateCar(CarDTO carDTO);

}
