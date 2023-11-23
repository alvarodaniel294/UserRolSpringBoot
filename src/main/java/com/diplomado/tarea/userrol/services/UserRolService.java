package com.diplomado.tarea.userrol.services;

import com.diplomado.tarea.userrol.dto.RolDTO;
import com.diplomado.tarea.userrol.dto.UserRolDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRolService {


    List<UserRolDTO> listUserRol();
    UserRolDTO create(UserRolDTO userRolDTO);

    UserRolDTO create(Integer userId, Integer rolId);

    Optional<UserRolDTO> findById(Integer userRolId);

    void inactivate(Integer userId, Integer rolId);

    void delete(Integer userId, Integer rolId);

    UserRolDTO partialUpdate(Integer userRolId, Map<String, Object> updates);
}
