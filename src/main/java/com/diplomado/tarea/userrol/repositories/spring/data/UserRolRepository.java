package com.diplomado.tarea.userrol.repositories.spring.data;

import com.diplomado.tarea.userrol.domain.entities.Rol;
import com.diplomado.tarea.userrol.domain.entities.UserRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRolRepository extends JpaRepository<UserRol, Integer> {

    List<UserRol> findAllByUser_IdAndActive(Integer userId, Boolean active);

    List<UserRol> findAllByRol_IdAndActive(Integer rolId, Boolean active);

    Optional<UserRol> findByUser_IdAndRol_IdAndActive(Integer userId, Integer rolId, Boolean active);

    void deleteByUser_IdAndRol_Id(Integer userId, Integer rolId);
}
