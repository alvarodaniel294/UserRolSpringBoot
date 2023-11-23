package com.diplomado.tarea.userrol.services.implement;

import com.diplomado.tarea.userrol.Exceptions.RoleNotFoundException;
import com.diplomado.tarea.userrol.Exceptions.UserNotFoundException;
import com.diplomado.tarea.userrol.domain.entities.Rol;
import com.diplomado.tarea.userrol.domain.entities.User;
import com.diplomado.tarea.userrol.domain.entities.UserRol;
import com.diplomado.tarea.userrol.dto.UserRolDTO;
import com.diplomado.tarea.userrol.repositories.spring.data.RolRepository;
import com.diplomado.tarea.userrol.repositories.spring.data.UserRepository;
import com.diplomado.tarea.userrol.repositories.spring.data.UserRolRepository;
import com.diplomado.tarea.userrol.services.UserRolService;
import com.diplomado.tarea.userrol.services.mapper.RolMapper;
import com.diplomado.tarea.userrol.services.mapper.UserRolMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserRolServiceImpl implements UserRolService {
    private final UserRolRepository userRolRepository;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final UserRolMapper userRolMapper;

    public UserRolServiceImpl(UserRolRepository userRolRepository, UserRepository userRepository, RolRepository rolRepository, UserRolMapper userRolMapper) {
        this.userRolRepository = userRolRepository;
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.userRolMapper = userRolMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<UserRolDTO> listUserRol() {
        return userRolRepository.findAll().stream().map(userRolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserRolDTO create(UserRolDTO userRolDTO) {
        UserRol userRol = new UserRol();
        User user = userRepository.findById(userRolDTO.getUserId()).orElseThrow(UserNotFoundException::new);
        Rol rol = rolRepository.findById(userRolDTO.getRolId()).orElseThrow(RoleNotFoundException::new);
        Optional<UserRol> findUserRol = userRolRepository.findByUser_IdAndRol_IdAndActive(userRolDTO.getUserId(), userRolDTO.getRolId(), true);
        if (findUserRol.isPresent()) {
            throw new IllegalArgumentException("already in db");
        }
        userRol.setUser(user);
        userRol.setRol(rol);
        userRol.setActive(true);
        userRol.setCreatedAt(LocalDateTime.now());
        UserRol userRolSaved = userRolRepository.save(userRol);
        UserRolDTO userRolDTO1 = new UserRolDTO();
        userRolDTO1.setActive(userRolSaved.getActive());
        userRolDTO1.setUserId(userRolSaved.getUser().getId());
        userRolDTO1.setRolId(userRolSaved.getRol().getId());
        userRolDTO1.setId(userRolSaved.getId());
        return userRolDTO1;
    }

    @Override
    public UserRolDTO create(Integer userId, Integer rolId) {
        UserRol userRol = new UserRol();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Rol rol = rolRepository.findById(rolId).orElseThrow(RoleNotFoundException::new);
        Optional<UserRol> findUserRol = userRolRepository.findByUser_IdAndRol_IdAndActive(userId, rolId, true);
        if (findUserRol.isPresent()) {
            throw new IllegalArgumentException("already in db");
        }
        userRol.setUser(user);
        userRol.setRol(rol);
        userRol.setActive(true);
        userRol.setCreatedAt(LocalDateTime.now());
        UserRol userRolSaved = userRolRepository.save(userRol);
        UserRolDTO userRolDTO1 = new UserRolDTO();
        userRolDTO1.setActive(userRolSaved.getActive());
        userRolDTO1.setUserId(userRolSaved.getUser().getId());
        userRolDTO1.setRolId(userRolSaved.getRol().getId());
        userRolDTO1.setId(userRolSaved.getId());
        return userRolDTO1;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserRolDTO> findById(Integer userRolId) {
        return userRolRepository.findById(userRolId).map(userRolMapper::toDto);
    }

    @Override
    public void inactivate(Integer userId, Integer rolId) {
        UserRol findUserRol = userRolRepository.findByUser_IdAndRol_IdAndActive(userId, rolId, true).orElseThrow(() -> new IllegalArgumentException("not found"));
        findUserRol.setActive(false);
        userRolRepository.save(findUserRol);

    }

    @Override
    public void delete(Integer userId, Integer rolId) {
        userRolRepository.deleteByUser_IdAndRol_Id(userId, rolId);
    }

    @Override
    public UserRolDTO partialUpdate(Integer userRolId, Map<String, Object> updates) {
        UserRol userRol = userRolRepository.findById(userRolId).orElseThrow(() -> new IllegalArgumentException("Not found"));
        updates.forEach((key, value) -> {
            switch (key) {
                case "active":
                    userRol.setActive((Boolean) value);
                    break;

                // Add more cases for other fields
            }
        });
        return userRolMapper.toDto(userRolRepository.save(userRol));
    }
}
