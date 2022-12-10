package com.course.trucks.controller;

import com.course.trucks.dto.AuthUserDTO;
import com.course.trucks.dto.RoleUserDTO;
import com.course.trucks.exception.InvalidPasswordException;
import com.course.trucks.exception.NotVerifiedException;
import com.course.trucks.exception.ServiceException;
import com.course.trucks.message.AnswerMessage;
import com.course.trucks.model.AuthenticationRequest;
import com.course.trucks.model.AuthenticationResponse;
import com.course.trucks.security.jwtProvider.JwtProvider;
import com.course.trucks.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class);

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthenticationController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AnswerMessage register(@RequestBody AuthUserDTO authUserDTO) throws ServiceException {
        LOGGER.info("Registration...");
        authUserDTO.setPassword(BCrypt.hashpw(authUserDTO.getPassword(), BCrypt.gensalt()));
        userService.register(authUserDTO);
        LOGGER.info("Registration has been finished.");
        return new AnswerMessage("User has been created.", HttpStatus.ACCEPTED.toString());
    }

    @PostMapping(value = "/sighIn", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthenticationResponse sighIn(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidPasswordException, NotVerifiedException {
        LOGGER.info("Login...");
        RoleUserDTO roleUserDTO = userService.getRoleUserByEmail(authenticationRequest.getLogin());
        if (Objects.isNull(userService.getUserByEmail(authenticationRequest.getLogin()).getSeriesPassportNumber())) {
            throw new NotVerifiedException("User hasn't been verified");
        }
        String token;
        if (BCrypt.checkpw(authenticationRequest.getPassword(), roleUserDTO.getPassword())) {
            token = jwtProvider.generateToken(authenticationRequest.getLogin());
            LOGGER.info("Successful!");
        } else {
            LOGGER.error("Invalid password!");
            throw new InvalidPasswordException("Wrong password");
        }
        return new AuthenticationResponse(roleUserDTO.getId(), token, roleUserDTO.getRole());
    }
}
