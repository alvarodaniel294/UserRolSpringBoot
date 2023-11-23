package com.diplomado.tarea.userrol.web.rest;

import com.diplomado.tarea.userrol.Exceptions.RoleNotFoundException;
import com.diplomado.tarea.userrol.dto.RolDTO;
import com.diplomado.tarea.userrol.services.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/roles")
public class RolController {
    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }


    @GetMapping
    public ResponseEntity<List<RolDTO>> listRoles() {
        return ResponseEntity.ok().body(rolService.listRol());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> findRolById(@PathVariable(name = "id") Integer id) {
        RolDTO rolDTO = rolService.findRolById(id).orElseThrow(RoleNotFoundException::new);
        return ResponseEntity.ok().body(rolDTO);
    }

    @PostMapping
    public ResponseEntity<RolDTO> create(@RequestBody RolDTO rolDTO) throws URISyntaxException {
        if (rolDTO.getId() != null) {
            throw new IllegalArgumentException("Invalid Id on creation");
        }
        RolDTO rolDTOSaved = rolService.create(rolDTO);
        return ResponseEntity.created(new URI("/v1/rol/" + rolDTOSaved.getId())).body(rolDTOSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> update(@RequestBody RolDTO rolDTO, @PathVariable(name = "id") Integer id) {
        if (rolDTO.getId() == null) {
            throw new IllegalArgumentException("RolId required");
        }
        if (!Objects.equals(rolDTO.getId(), id)) {
            throw new IllegalArgumentException("Invalid Id");
        }

        return ResponseEntity.ok().body(rolService.update(rolDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRolById(@PathVariable(name = "id") Integer id) {
        rolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
