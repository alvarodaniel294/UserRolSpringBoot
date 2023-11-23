package com.diplomado.tarea.userrol.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRolDTO {

    private Integer id;
    private boolean active;
    private Integer userId;
    private Integer rolId;
}
