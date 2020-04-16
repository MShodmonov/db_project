package dreamteam.db_project.controllers;

import dreamteam.db_project.model.Management;
import dreamteam.db_project.repository.ManagementRepo;
import dreamteam.db_project.service.ManagementService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/api")
public class ManagementController {

    final
    ManagementRepo managementRepo;
    final ManagementService managementService;

    public ManagementController(ManagementRepo managementRepo, ManagementService managementService) {
        this.managementRepo = managementRepo;
        this.managementService = managementService;
    }


    @GetMapping("/managements/{id}")
    public HttpEntity<Management> getManagementById(@PathVariable(name = "id") String id)
    {
        Optional<Management> optionalManagement = managementRepo.findById(UUID.fromString(id));
        if (optionalManagement.isPresent())
        {
            return ResponseEntity.ok(optionalManagement.get());
        }
        else return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/managements/{id}")
    HttpEntity<Management> deleteManagementBYId(@PathVariable String id)
    {
        Optional<Management> optionalManagement = managementRepo.findById(UUID.fromString(id));
        if (optionalManagement.isPresent())
        {
            managementRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/managements")
    HttpEntity<Management> saveManagement(@RequestBody Management management)
    {
            if (management.getFullName()== null)
            {
                return ResponseEntity.noContent().build();
            }
            else
            {
                Management model=new Management(management.getFullName(),management.getUsername(),management.getPassword(),management.getBioInfo());
                Management save = managementRepo.save(model);
                return ResponseEntity.status(HttpStatus.CREATED).body(save);
            }
    }
    @PutMapping("/managements/{id}")
    HttpEntity<Management> updateManagement(@RequestBody Management management,@PathVariable String id)
    {
        Optional<Management> optionalManagement = managementRepo.findById(UUID.fromString(id));
        if (!optionalManagement.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            management.setId(UUID.fromString(id));
            Management save = managementRepo.save(management);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }
    @GetMapping("/managements")
    HttpEntity<List<Management>> getManagementList()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(managementRepo.findAll());
    }

    @GetMapping("/managements/fullname/{fullName}")
    HttpEntity<List<Management>> findManagerByFullName(@PathVariable(name = "fullName") String name)
    {
        return ResponseEntity.ok(managementService.findManagersByName(name));
    }
    @GetMapping("/managements/username/{username}")
    HttpEntity<Management> findManagerByUsername(@PathVariable(name = "username") String username)
    {
        Management managerByUsername = managementService.findManagerByUsername(username);

        if (managerByUsername.getUsername() == null)
        {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(managerByUsername);
    }

}
