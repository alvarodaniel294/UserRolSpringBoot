package com.diplomado.tarea.userrol.services.mapper;

import com.diplomado.tarea.userrol.domain.entities.User;
import com.diplomado.tarea.userrol.domain.entities.UserDetail;
import com.diplomado.tarea.userrol.dto.UserDTO;
import com.diplomado.tarea.userrol.dto.UserDetailDTO;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UserMapper implements CustomMapper<UserDTO, User> {
    @Override
    public UserDTO toDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }

    public UserDTO toDtoDetailed(User user) {
        UserDTO userDTO = new UserDTO();
        if (user.getUserDetail() != null) {
            UserDetailDTO userDetailDTO = new UserDetailDTO(
                    user.getUserDetail().getId(),
                    user.getUserDetail().getFirstName(),
                    user.getUserDetail().getLastName(),
                    user.getUserDetail().getAge(),
                    user.getUserDetail().getBirthDay()
            );
            userDTO.setUserDetailDTO(userDetailDTO);
        } else {
            userDTO.setUserDetailDTO(null);
        }
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCreatedAt(userDTO.getCreatedAt());
        return user;
    }
}
