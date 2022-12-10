package com.course.trucks.service.impl;

import com.course.trucks.dao.CarDAO;
import com.course.trucks.dto.CarDTO;
import com.course.trucks.mapper.CarMapper;
import com.course.trucks.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarServiceImpl implements CarService {

    private final CarMapper carMapper;
    private final CarDAO carDAO;

    @Autowired
    public CarServiceImpl(CarMapper carMapper, CarDAO carDAO) {
        this.carMapper = carMapper;
        this.carDAO = carDAO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertCar(CarDTO carDTO) {
        carDAO.save(carMapper.mapToModel(carDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCar(CarDTO carDTO) {
        carDAO.save(carMapper.mapToModel(carDTO));
    }
}
