package com.diplomado.tarea.userrol.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private LocalDateTime createdAt;
    private UserDetailDTO userDetailDTO;
}
