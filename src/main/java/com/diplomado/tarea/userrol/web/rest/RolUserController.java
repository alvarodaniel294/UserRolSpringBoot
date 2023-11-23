package com.diplomado.tarea.userrol.web.rest;

import com.diplomado.tarea.userrol.Exceptions.UserNotFoundException;
import com.diplomado.tarea.userrol.dto.UserDTO;
import com.diplomado.tarea.userrol.dto.UserRolDTO;
import com.diplomado.tarea.userrol.services.UserRolService;
import com.diplomado.tarea.userrol.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/roles")
public class RolUserController {


    private final UserService userService;
    private final UserRolService userRolService;

    public RolUserController(UserService userService, UserRolService userRolService) {
        this.userService = userService;
        this.userRolService = userRolService;
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<UserDTO>> getUsersByRolId(@PathVariable(name = "id") Integer id) {
        List<UserDTO> list = userService.getUsersByRolId(id);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/{id}/users")
    public ResponseEntity<UserDTO> createUserWithRolId(@PathVariable(name = "id") Integer id, @RequestBody UserDTO userDTO) {
        if (userDTO.getId() == null) {
            throw new IllegalArgumentException("Id required");
        }
        UserRolDTO userSaved = userRolService.create(userDTO.getId(), id);
        UserDTO userDTO1 = userService.getUserById(userSaved.getUserId()).orElseThrow(UserNotFoundException::new);
        return ResponseEntity.ok().body(userDTO1);
    }

    @DeleteMapping("/{id}/users/{userId}")
    public ResponseEntity<Void> createRolWithUserid(@PathVariable(name = "id") Integer id, @PathVariable(name = "userId") Integer userId) {
        if (id == null) {
            throw new IllegalArgumentException("Id required");
        }
        if (userId == null) {
            throw new IllegalArgumentException("Id required");
        }
        userRolService.delete(userId, id);
        return ResponseEntity.noContent().build();
    }
}
