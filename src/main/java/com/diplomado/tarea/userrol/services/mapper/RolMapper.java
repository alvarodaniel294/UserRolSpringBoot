package com.diplomado.tarea.userrol.services.mapper;

import com.diplomado.tarea.userrol.domain.entities.Rol;
import com.diplomado.tarea.userrol.dto.RolDTO;
import org.springframework.stereotype.Component;

@Component
public class RolMapper implements CustomMapper<RolDTO, Rol> {
    @Override
    public RolDTO toDto(Rol rol) {
        return new RolDTO(rol.getId(), rol.getName());
    }

    @Override
    public Rol toEntity(RolDTO rolDTO) {
        Rol rol = new Rol();
        rol.setName(rolDTO.getName());
        rol.setId(rolDTO.getId());
        return rol;
    }
}
