package dreamteam.db_project.controllers;

import dreamteam.db_project.model.Management;
import dreamteam.db_project.model.Roles;
import dreamteam.db_project.repository.ManagementRepo;
import dreamteam.db_project.repository.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RoleController {
   final
   RolesRepo rolesRepo;

    public RoleController(RolesRepo rolesRepo) {
        this.rolesRepo = rolesRepo;
    }

    @GetMapping("/role/{id}")
    public HttpEntity<Roles> getRolesById(@PathVariable String id)
    {
        Optional<Roles> optionalRoles = rolesRepo.findById(UUID.fromString(id));
        if (optionalRoles.isPresent())
        {
            return ResponseEntity.ok(optionalRoles.get());
        }
        else return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/role/{id}")
    HttpEntity<Management> deleteRoleBYId(@PathVariable String id)
    {
        Optional<Roles> optionalRoles = rolesRepo.findById(UUID.fromString(id));
        if (optionalRoles.isPresent())
        {
            rolesRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/role")
    HttpEntity<Roles> saveRoles(@RequestBody Roles roles)
    {
        if (roles.getId()== null)
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            Roles save = rolesRepo.save(roles);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }
    }
    @PutMapping("/roles/{id}")
    HttpEntity<Roles> updateRole(@RequestBody Roles roles,@PathVariable String id)
    {
        Optional<Roles> optionalRoles = rolesRepo.findById(UUID.fromString(id));
        if (!optionalRoles.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            roles.setId(UUID.fromString(id));
            Roles save = rolesRepo.save(roles);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }
    @GetMapping("/role")
    HttpEntity<List<Roles>> getRoleList()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(rolesRepo.findAll());
    }
}
