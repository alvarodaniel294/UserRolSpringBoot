package com.diplomado.tarea.userrol.web.rest;

import com.diplomado.tarea.userrol.dto.UserRolDTO;
import com.diplomado.tarea.userrol.services.UserRolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/user-rol")
public class UserAndRolController {

    private final UserRolService userRolService;

    public UserAndRolController(UserRolService userRolService) {
        this.userRolService = userRolService;
    }

    @GetMapping
    public ResponseEntity<List<UserRolDTO>> listUserRol() {
        return ResponseEntity.ok().body(userRolService.listUserRol());
    }

    @GetMapping("/{userRolId}")
    public ResponseEntity<UserRolDTO> findById(@PathVariable(name = "userRolId") Integer userRolId) {
        return ResponseEntity.ok().body(userRolService.findById(userRolId).orElseThrow(() -> new IllegalArgumentException()));
    }

    @PostMapping
    public ResponseEntity<UserRolDTO> create(@RequestBody UserRolDTO userRolDTO) throws URISyntaxException {
        UserRolDTO userRolSaved = userRolService.create(userRolDTO);

        return ResponseEntity.created(new URI("/v1/user-rol/" + userRolSaved.getId())).body(userRolSaved);
    }

    @PatchMapping("/{userRolId}")
    public ResponseEntity<UserRolDTO> partialUpdate(@PathVariable(name = "userRolId") Integer id,
                                                    @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok().body(userRolService.partialUpdate(id, updates));
    }

}