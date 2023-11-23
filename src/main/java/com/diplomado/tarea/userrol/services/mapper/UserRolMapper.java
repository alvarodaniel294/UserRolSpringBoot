package com.diplomado.tarea.userrol.services.mapper;

import com.diplomado.tarea.userrol.domain.entities.UserRol;
import com.diplomado.tarea.userrol.dto.UserRolDTO;
import org.springframework.stereotype.Component;

@Component
public class UserRolMapper implements CustomMapper<UserRolDTO, UserRol> {
    @Override
    public UserRolDTO toDto(UserRol userRol) {
        return new UserRolDTO(userRol.getId(), userRol.getActive(), userRol.getUser().getId(), userRol.getRol().getId());
    }

    @Override
    public UserRol toEntity(UserRolDTO userRolDTO) {
        return null;
    }
}
