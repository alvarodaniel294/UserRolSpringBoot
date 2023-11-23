package com.diplomado.tarea.userrol.services;

import com.diplomado.tarea.userrol.domain.entities.User;
import com.diplomado.tarea.userrol.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> listUsers();

    List<UserDTO> listUsersDetailed();
    Optional<UserDTO> getUserById(Integer userId);

    UserDTO save(UserDTO userDTO);

    void deleteById(Integer userId);

    List<UserDTO> getUsersByRolId(Integer rolId);
}
