package com.artur.orange_backend.controller;

import com.artur.orange_backend.dto.UserCreationDTO;
import com.artur.orange_backend.dto.UserViewDTO;
import com.artur.orange_backend.dto.mappers.UserMapper;
import com.artur.orange_backend.model.User;
import com.artur.orange_backend.service.UserConfirmationService;
import com.artur.orange_backend.service.UserService;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
    private final String PASSWORD_NOT_MATCHING_MSG = "Passwords %s and %s are not matching";
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserConfirmationService userConfirmationService;

    @GetMapping
    public ResponseEntity<List<UserViewDTO>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        List<UserViewDTO> userViewDTOs = users.stream().map(userMapper::userToUserViewDTO).toList();
        return new ResponseEntity<>(userViewDTOs, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserViewDTO> getUserById(@PathVariable("userId") Long userId){
        User user = userService.getUserById(userId);
        UserViewDTO userViewDTO = userMapper.userToUserViewDTO(user);
        return new ResponseEntity<>(userViewDTO, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public void updateUserById(@PathVariable("userId") Long userId,
                               @Valid @RequestBody UserCreationDTO userCreationDTO){
        if (!Objects.equals(userCreationDTO.getPassword(), userCreationDTO.getMatchingPassword())){
            throw new IllegalStateException(String.format(PASSWORD_NOT_MATCHING_MSG,
                    userCreationDTO.getPassword(), userCreationDTO.getMatchingPassword()));
        }
        User user = userMapper.userCreationDTOtoUser(userCreationDTO);

        userConfirmationService.updateUserById(userId, user);
    }

    @PutMapping(path = "/addOrder/{orderId}/toUser/{userId}")
    public void addOrderToUserById(@PathVariable("orderId") Long orderId, @PathVariable("userId") Long userId){
        userService.addOrderToUserById(orderId, userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable("userId") Long userId){
        userService.deleteUserById(userId);
    }

}
