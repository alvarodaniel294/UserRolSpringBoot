package com.diplomado.tarea.userrol.web.rest;

import com.diplomado.tarea.userrol.dto.RolDTO;
import com.diplomado.tarea.userrol.dto.UserRolDTO;
import com.diplomado.tarea.userrol.services.RolService;
import com.diplomado.tarea.userrol.services.UserRolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/users")
public class UserRolController {

    private final RolService rolService;
    private final UserRolService userRolService;

    public UserRolController(RolService rolService, UserRolService userRolService) {
        this.rolService = rolService;
        this.userRolService = userRolService;
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<List<RolDTO>> getRolesByUserId(@PathVariable(name = "id") Integer id) {
        List<RolDTO> list = rolService.listRolesByUser(id);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<RolDTO> createRolWithUserid(@PathVariable(name = "id") Integer id, @RequestBody RolDTO rolDTO) {
        if (rolDTO.getId() == null) {
            throw new IllegalArgumentException("Id required");
        }
        UserRolDTO userSaved = userRolService.create(id, rolDTO.getId());
        RolDTO rolDTO1 = rolService.findRolById(userSaved.getRolId()).orElseThrow(() -> new IllegalArgumentException("NOt valid"));
        return ResponseEntity.ok().body(rolDTO1);
    }

    @DeleteMapping("/{id}/roles/{rolId}")
    public ResponseEntity<Void> createRolWithUserid(@PathVariable(name = "id") Integer id, @PathVariable(name = "rolId") Integer rolId) {
        if (id == null) {
            throw new IllegalArgumentException("Id required");
        }
        if (rolId == null){
            throw new IllegalArgumentException("Id required");
        }
        userRolService.delete(id, rolId);
        return ResponseEntity.noContent().build();
    }
}