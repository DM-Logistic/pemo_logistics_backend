package com.course.trucks.service.impl;

import com.course.trucks.dao.CarDAO;
import com.course.trucks.dao.UserDAO;
import com.course.trucks.dto.*;
import com.course.trucks.exception.ServiceException;
import com.course.trucks.mapper.CarMapper;
import com.course.trucks.mapper.UserMapper;
import com.course.trucks.model.Car;
import com.course.trucks.service.UserService;
import com.course.trucks.util.Base64Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final CarMapper carMapper;
    private final UserDAO userDAO;
    private final CarDAO carDAO;
    private final Base64Converter base64Converter;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserDAO userDAO, CarMapper carMapper, CarDAO carDAO, Base64Converter base64Converter) {
        this.userMapper = userMapper;
        this.userDAO = userDAO;
        this.carMapper = carMapper;
        this.carDAO = carDAO;
        this.base64Converter = base64Converter;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createUser(UserDTO userDTO) {
        userDAO.save(userMapper.mapToUser(userDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(UserDTO userDTO) {
        if (Objects.nonNull(userDTO.getCarDTO())) {
            Car car = carMapper.mapToModel(userDTO.getCarDTO());
            carDAO.save(car);
            userDTO.setCarDTO(carMapper.mapToDto(car));
            String imagePath = null;
            String imageStr = userDTO.getAvatarImageStr();
            if (Objects.nonNull(imageStr)) {
                imagePath = base64Converter.saveImage(imageStr, userDTO.getEmail());
            }
            userDAO.updateUser(userDTO.getId(), userDTO.getPhone(), userDTO.getDrivingLicense(),
                    userDTO.getCitizenship(), userDTO.getSeriesPassportNumber(), userDTO.getVisas(),
                    userDTO.getRole(), userDTO.isActive(), carMapper.mapToModel(userDTO.getCarDTO()), imagePath,
                    userDTO.getName(), userDTO.getSurname());
        } else {
            String imagePath = null;
            String imageStr = userDTO.getAvatarImageStr();
            if (Objects.nonNull(imageStr)) {
                imagePath = base64Converter.saveImage(imageStr, userDTO.getEmail());
            }
            userDAO.updateUser(userDTO.getId(), userDTO.getPhone(), userDTO.getDrivingLicense(),
                    userDTO.getCitizenship(), userDTO.getSeriesPassportNumber(), userDTO.getVisas(),
                    userDTO.getRole(), userDTO.isActive(), null, imagePath, userDTO.getName(), userDTO.getSurname());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<UserDTO> getUsers() {
        return userDAO.findAll().stream().map(userMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RoleUserDTO getRoleUserByEmail(String email) {
        return userMapper.mapToRoleUserDto(userDAO.findUserByEmail(email));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserDTO getUserByEmail(String email) {
        return userMapper.mapToUserDtoWithoutCar(userDAO.findUserByEmail(email));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserDTO getUserById(long id) {
        return userMapper.mapToUserDto(userDAO.getById(id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUser(long id) {
        userDAO.deleteUserFromLinkTableById(id);
        userDAO.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Set<TaskDTO> getTasksByUserId(long userId) {
        UserDTO userDTO = getUserById(userId);
        return userDTO.getTasks();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void assignTaskToUser(long userId, long taskId) {
        userDAO.reAssignTask(taskId);
        userDAO.assignTask(userId, taskId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(AuthUserDTO authUserDTO) throws ServiceException {
        if (userDAO.getUsersCountByEmail(authUserDTO.getEmail()) != 0) {
            throw new ServiceException("User with this email is already exists");
        }
        userDAO.save(userMapper.mapFromAuthUser(authUserDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateIsActiveById(long userId, boolean isActive) {
        userDAO.updateIsActiveById(userId, isActive);
    }
}
