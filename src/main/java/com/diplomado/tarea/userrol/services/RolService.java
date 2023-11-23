package com.diplomado.tarea.userrol.services;

import com.diplomado.tarea.userrol.domain.entities.Rol;
import com.diplomado.tarea.userrol.dto.RolDTO;

import java.util.List;
import java.util.Optional;

public interface RolService {

    List<RolDTO> listRol();

    Optional<RolDTO> findRolById(Integer rolId);

    RolDTO create(RolDTO rolDTO);

    RolDTO update(RolDTO rolDTO);

    void deleteById(Integer rolId);

    List<RolDTO> listRolesByUser(Integer userId);

}
