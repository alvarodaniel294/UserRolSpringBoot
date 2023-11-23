package com.diplomado.tarea.userrol.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_detail")
public class UserDetail {

    @Id
    @SequenceGenerator(name = "user_detail_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_detail_sequence")
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(name = "first_name",nullable = false, length = 100)
    private String firstName;

    @NotNull
    @Size(max = 100)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "birth_day")
    private Date birthDay;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userEntity;

}
