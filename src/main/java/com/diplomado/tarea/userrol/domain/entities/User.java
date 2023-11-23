package com.diplomado.tarea.userrol.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_db")
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Integer id;
    @NotNull
    @Size(max = 150)
    @Column(name = "username", nullable = false, length = 150)
    private String userName;

    @NotNull
    @Size(max = 150)
    @Column(name = "password", nullable = false, length = 150)
    private String password;

    @NotNull
    @Size(max = 150)
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserDetail userDetail;
}
