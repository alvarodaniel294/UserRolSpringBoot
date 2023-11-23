package com.diplomado.tarea.userrol.web.rest;

import com.diplomado.tarea.userrol.Exceptions.UserNotFoundException;
import com.diplomado.tarea.userrol.dto.RolDTO;
import com.diplomado.tarea.userrol.dto.UserDTO;
import com.diplomado.tarea.userrol.services.RolService;
import com.diplomado.tarea.userrol.services.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Retrieve a list of all users")
    @GetMapping
    public ResponseEntity<List<UserDTO>> listAllUsers(@RequestParam(required = false, defaultValue = "false", name = "detailed") boolean detailed) {
        if (detailed) {
            return ResponseEntity
                    .ok()
                    .body(userService.listUsersDetailed());
        } else {
            return ResponseEntity
                    .ok()
                    .body(userService.listUsers());

        }

    }

    @Operation(summary = "Retrieve a single user with a given id")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUseById(@PathVariable(name = "id") final Integer userId) {
        return ResponseEntity
                .ok()
                .body(userService.getUserById(userId)
                        .orElseThrow(UserNotFoundException::new));
    }

    @Operation(summary = "Creates user")
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody final UserDTO userDTO) throws URISyntaxException {
        if (userDTO.getId() != null) {
            throw new IllegalArgumentException("I new user cannot already have an id.");
        }
        userDTO.setCreatedAt(LocalDateTime.now());
        UserDTO user = userService.save(userDTO);
        return ResponseEntity
                .created(new URI("/v1/users/" + user.getId()))
                .body(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable(name = "id") final Integer id,
                                          @RequestBody final UserDTO userDto) {
        if (userDto.getId() == null) {
            throw new IllegalArgumentException("UserId can not be null");
        }
        if (!Objects.equals(userDto.getId(), id)) {
            throw new IllegalArgumentException("Invalid UserId");
        }

        if (userDto.getUserDetailDTO() != null && userDto.getUserDetailDTO().getUserDetailId() == null) {
            throw new IllegalArgumentException("Invalid userDetailId");
        }
        return ResponseEntity.ok().body(userService.save(userDto));
    }


}
