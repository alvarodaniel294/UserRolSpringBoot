package com.diplomado.tarea.userrol.repositories.spring.data;

import com.diplomado.tarea.userrol.domain.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
}
