package com.course.trucks.controller;

import com.course.trucks.dto.CarDTO;
import com.course.trucks.service.CarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final static Logger LOGGER = Logger.getLogger(CarController.class);

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void insertCar(@RequestBody CarDTO carDTO) {
        LOGGER.info("Inserting car...");
        carService.insertCar(carDTO);
        LOGGER.info("Car was inserted.");
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateCar(@RequestBody CarDTO carDTO, @PathVariable long id) {
        LOGGER.info("Updating...");
        carDTO.setId(id);
        carService.updateCar(carDTO);
        LOGGER.info("Car was updated.");
    }
}
