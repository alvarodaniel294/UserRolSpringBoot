package com.diplomado.tarea.userrol.services.implement;

import com.diplomado.tarea.userrol.domain.entities.User;
import com.diplomado.tarea.userrol.domain.entities.UserDetail;
import com.diplomado.tarea.userrol.domain.entities.UserRol;
import com.diplomado.tarea.userrol.dto.UserDTO;
import com.diplomado.tarea.userrol.repositories.spring.data.UserDetailRepository;
import com.diplomado.tarea.userrol.repositories.spring.data.UserRepository;
import com.diplomado.tarea.userrol.repositories.spring.data.UserRolRepository;
import com.diplomado.tarea.userrol.services.UserService;
import com.diplomado.tarea.userrol.services.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final UserRolRepository userRolRepository;
    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserDetailRepository userDetailRepository, UserRolRepository userRolRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userDetailRepository = userDetailRepository;
        this.userRolRepository = userRolRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> listUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> listUsersDetailed() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDtoDetailed)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserById(Integer userId) {
        return userRepository
                .findById(userId)
                .map(userMapper::toDtoDetailed);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userRepository.save(userMapper.toEntity(userDTO));
        if (userDTO.getUserDetailDTO() != null) {
            UserDetail userDetail = new UserDetail();
            userDetail.setId(userDTO.getUserDetailDTO().getUserDetailId());
            userDetail.setFirstName(userDTO.getUserDetailDTO().getFirstName());
            userDetail.setLastName(userDTO.getUserDetailDTO().getLastName());
            userDetail.setAge(userDTO.getUserDetailDTO().getAge());
            userDetail.setBirthDay(userDTO.getUserDetailDTO().getBirthDate());
            userDetail.setUserEntity(user);
            userDetailRepository.save(userDetail);
        }
        return userMapper.toDtoDetailed(user);
    }

    @Override
    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDTO> getUsersByRolId(Integer rolId) {
        List<UserRol> userRolList = userRolRepository.findAllByRol_IdAndActive(rolId, true);
        return userRolList.stream().map( (userRol) -> userMapper.toDto(userRol.getUser()) ).collect(Collectors.toList());
    }
}
