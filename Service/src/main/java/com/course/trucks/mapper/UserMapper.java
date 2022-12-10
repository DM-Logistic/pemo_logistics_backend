package com.course.trucks.mapper;

import com.course.trucks.dto.AuthUserDTO;
import com.course.trucks.dto.RoleUserDTO;
import com.course.trucks.dto.UserDTO;
import com.course.trucks.model.User;
import com.course.trucks.util.Base64Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private static final String USER_ROLE = "ROLE_Driver";

    private final TaskMapper taskMapper;
    private final CarMapper carMapper;
    private final Base64Converter base64Converter;

    @Autowired
    public UserMapper(TaskMapper taskMapper, CarMapper carMapper, Base64Converter base64Converter) {
        this.taskMapper = taskMapper;
        this.carMapper = carMapper;
        this.base64Converter = base64Converter;
    }

    public User mapFromAuthUser(AuthUserDTO authUserDTO) {
        return new User(authUserDTO.getId(), authUserDTO.getName(), authUserDTO.getSurname(), authUserDTO.getEmail(),
                authUserDTO.getPhone(), authUserDTO.getPassword(), null, null, null, null, USER_ROLE, false, null,
                null, null);
    }

    public RoleUserDTO mapToRoleUserDto(User user) {
        return new RoleUserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }

    public UserDTO mapToUserDto(User user) {
        UserDTO userDTO;
        if (Objects.isNull(user.getCar())) {
            userDTO = new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getEmail(),
                user.getPhone(), user.getDrivingLicense(), user.getCitizenship(), user.getSeriesPassportNumber(),
                user.getVisas(), user.getRole(), user.isActive(), null,
                user.getTasks().stream().map(taskMapper::mapToDto).collect(Collectors.toSet()),
                null);
        } else {
            userDTO = new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getEmail(),
                user.getPhone(), user.getDrivingLicense(), user.getCitizenship(), user.getSeriesPassportNumber(),
                user.getVisas(), user.getRole(), user.isActive(), null,
                user.getTasks().stream().map(taskMapper::mapToDto).collect(Collectors.toSet()),
                carMapper.mapToDto(user.getCar()));
        }
        String avatarImagePath = user.getAvatarImagePath();
        if (Objects.nonNull(avatarImagePath)) {
            userDTO.setAvatarImageStr(base64Converter.getBase64FromImagePath(user.getAvatarImagePath()));
        }
        return userDTO;
    }

    public UserDTO mapToUserDtoWithoutCar(User user) {
        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getEmail(),
                user.getPhone(), user.getDrivingLicense(), user.getCitizenship(), user.getSeriesPassportNumber(),
                user.getVisas(), user.getRole(), user.isActive(), null,
                user.getTasks().stream().map(taskMapper::mapToDto).collect(Collectors.toSet()),
                null);
        String avatarImagePath = user.getAvatarImagePath();
        if (Objects.nonNull(avatarImagePath)) {
            userDTO.setAvatarImageStr(base64Converter.getBase64FromImagePath(user.getAvatarImagePath()));
        }
        return userDTO;
    }

    public User mapToUser(UserDTO userDTO) {
        User user = new User(userDTO.getId(), userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(),
                userDTO.getPhone(), null, userDTO.getDrivingLicense(), userDTO.getCitizenship(),
                userDTO.getSeriesPassportNumber(), userDTO.getVisas(), userDTO.getRole(),
                userDTO.isActive(), null, carMapper.mapToModel(userDTO.getCarDTO()),
                userDTO.getTasks().stream().map(taskMapper::mapToModel).collect(Collectors.toSet()));
        String avatarImageStr = userDTO.getAvatarImageStr();
        if (Objects.nonNull(avatarImageStr)) {
            user.setAvatarImagePath(base64Converter.saveImage(userDTO.getAvatarImageStr(), userDTO.getEmail()));
        }
        return user;
    }
}
