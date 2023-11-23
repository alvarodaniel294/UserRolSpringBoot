package com.diplomado.tarea.userrol.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailDTO {
    private Integer userDetailId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Date birthDate;
}
