package com.artur.orange_backend.controller;

import com.artur.orange_backend.dto.UserCreationDTO;
import com.artur.orange_backend.dto.mappers.UserMapper;
import com.artur.orange_backend.model.User;
import com.artur.orange_backend.service.UserConfirmationService;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "api/registration")
public class RegistrationController {
    private final String PASSWORD_NOT_MATCHING_MSG = "Passwords %s and %s are not matching";
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Autowired
    private UserConfirmationService userConfirmationService;

    @PostMapping
    public void registerUser(@Valid @RequestBody UserCreationDTO userCreationDTO){
        if (!Objects.equals(userCreationDTO.getPassword(), userCreationDTO.getMatchingPassword())){
            throw new IllegalStateException(String.format(PASSWORD_NOT_MATCHING_MSG,
                    userCreationDTO.getPassword(), userCreationDTO.getMatchingPassword()));
        }
        User user = userMapper.userCreationDTOtoUser(userCreationDTO);

        userConfirmationService.addUser(user);
    }

    @GetMapping("/confirm")
    public void confirm(@RequestParam("token") String token) {
        userConfirmationService.confirmToken(token);
    }
}
