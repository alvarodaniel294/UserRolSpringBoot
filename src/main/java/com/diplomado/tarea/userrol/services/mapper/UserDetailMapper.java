package com.diplomado.tarea.userrol.services.mapper;

import com.diplomado.tarea.userrol.domain.entities.UserDetail;
import com.diplomado.tarea.userrol.dto.UserDetailDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDetailMapper implements CustomMapper<UserDetailDTO, UserDetail> {
    @Override
    public UserDetailDTO toDto(UserDetail userDetail) {
        return new UserDetailDTO(
                userDetail.getId(),
                userDetail.getFirstName(),
                userDetail.getLastName(),
                userDetail.getAge(),
                userDetail.getBirthDay()
        );
    }

    @Override
    public UserDetail toEntity(UserDetailDTO userDetailDTO) {
        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName(userDetailDTO.getFirstName());
        userDetail.setLastName(userDetailDTO.getLastName());
        userDetail.setAge(userDetailDTO.getAge());
        userDetail.setBirthDay(userDetailDTO.getBirthDate());
        return userDetail;
    }
}
