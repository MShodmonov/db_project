package dreamteam.db_project.controllers;

import dreamteam.db_project.model.Stuffs;
import dreamteam.db_project.repository.StuffRepo;
import dreamteam.db_project.service.StuffService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class staffController {


        private final StuffRepo stuffRepo;
        private final StuffService stuffService;

    public staffController(StuffRepo stuffRepo, StuffService stuffService) {
        this.stuffRepo = stuffRepo;
        this.stuffService = stuffService;
    }


    @GetMapping("/stuffs/{id}")
    public HttpEntity<Stuffs> getStuffById(@PathVariable(name = "id") String id) {
        Optional<Stuffs> optionalStuffs = stuffRepo.findById(UUID.fromString(id));
        return optionalStuffs.<HttpEntity<Stuffs>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/stuffs/{id}")
    HttpEntity<Stuffs> deleteStuffBYId(@PathVariable String id) {
        Optional<Stuffs> optionalStuffs = stuffRepo.findById(UUID.fromString(id));
        if (optionalStuffs.isPresent()) {
            stuffRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping("/stuffs")
    HttpEntity<Stuffs> saveStuff(@RequestBody Stuffs stuffs) {
        if (stuffs.getUsername() == null) {
            return ResponseEntity.noContent().build();
        } else {
            Stuffs model = new Stuffs(stuffs.getFullName(),stuffs.getUsername(),stuffs.getPassword(),stuffs.getBioInfo(),stuffs.getRoles(),stuffs.getPhoneNumber());
            Stuffs save = stuffRepo.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }
    }

    @PutMapping("/stuffs/{id}")
    HttpEntity<Stuffs> updateStuff(@RequestBody Stuffs stuffs, @PathVariable String id) {
        Optional<Stuffs> optionalStuffs = stuffRepo.findById(UUID.fromString(id));
        if (!optionalStuffs.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            stuffs.setId(UUID.fromString(id));
            Stuffs save = stuffRepo.save(stuffs);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }

    @GetMapping("/stuffs")
    HttpEntity<List<Stuffs>> getStuffsList() {
        return ResponseEntity.status(HttpStatus.CREATED).body(stuffRepo.findAll());
    }

    @GetMapping("/stuffs/fullname/{fullName}")
    HttpEntity<List<Stuffs>> findStuffsByFullName(@PathVariable(name = "fullName") String name) {
        return ResponseEntity.ok(stuffService.findStuffsByName(name));
    }

    @GetMapping("/stuffs/username/{username}")
    HttpEntity<Stuffs> findStuffByUsername(@PathVariable(name = "username") String username) {
        Stuffs stuffByUsername = stuffService.findStuffsByUsername(username);

        if (stuffByUsername.getUsername() == null) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok(stuffByUsername);
    }

}



