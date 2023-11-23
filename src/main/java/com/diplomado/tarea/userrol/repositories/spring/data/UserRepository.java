package com.diplomado.tarea.userrol.repositories.spring.data;

import com.diplomado.tarea.userrol.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
