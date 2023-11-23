package com.diplomado.tarea.userrol.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "rol_db")
public class Rol {
    @Id
    @SequenceGenerator(name = "rol_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_sequence")
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;
}
