package com.artur.orange_backend.dto.mappers;

import com.artur.orange_backend.dto.UserCreationDTO;
import com.artur.orange_backend.dto.UserViewDTO;
import com.artur.orange_backend.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User userCreationDTOtoUser(UserCreationDTO dto);
    UserViewDTO userToUserViewDTO(User user);
}
