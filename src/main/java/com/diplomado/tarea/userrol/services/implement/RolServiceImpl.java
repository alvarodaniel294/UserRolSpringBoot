package com.diplomado.tarea.userrol.services.implement;

import com.diplomado.tarea.userrol.domain.entities.Rol;
import com.diplomado.tarea.userrol.domain.entities.UserRol;
import com.diplomado.tarea.userrol.dto.RolDTO;
import com.diplomado.tarea.userrol.repositories.spring.data.RolRepository;
import com.diplomado.tarea.userrol.repositories.spring.data.UserRolRepository;
import com.diplomado.tarea.userrol.services.RolService;
import com.diplomado.tarea.userrol.services.mapper.RolMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final UserRolRepository userRolRepository;
    private final RolMapper rolMapper;

    public RolServiceImpl(RolRepository rolRepository, UserRolRepository userRolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.userRolRepository = userRolRepository;
        this.rolMapper = rolMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolDTO> listRol() {
        return rolRepository.findAll().stream().map(rolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RolDTO> findRolById(Integer rolId) {
        return rolRepository.findById(rolId).map(rolMapper::toDto);
    }

    @Override
    public RolDTO create(RolDTO rolDTO) {
        Rol rol = rolRepository.save(rolMapper.toEntity(rolDTO));
        return rolMapper.toDto(rol);
    }

    @Override
    public RolDTO update(RolDTO rolDTO) {
        Rol rol = rolRepository.save(rolMapper.toEntity(rolDTO));
        return rolMapper.toDto(rol);
    }

    @Override
    public void deleteById(Integer rolId) {
        rolRepository.deleteById(rolId);
    }

    @Override
    public List<RolDTO> listRolesByUser(Integer userId) {
        List<UserRol> userRolList = userRolRepository.findAllByUser_IdAndActive(userId, true);
        return userRolList.stream().map( (userRol) -> rolMapper.toDto(userRol.getRol()) ).collect(Collectors.toList());
    }
}
