package dreamteam.db_project.controllers;

import dreamteam.db_project.model.Management;
import dreamteam.db_project.model.ZReports;
import dreamteam.db_project.repository.ZReportRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/api")
public class ZReportsController {

    private final ZReportRepo zReportRepo;

    public ZReportsController(ZReportRepo zReportRepo) {
        this.zReportRepo = zReportRepo;
    }

    @GetMapping("/z_reports/{id}")
    public HttpEntity<ZReports> getZReports(@PathVariable(name = "id") String id)
    {
        Optional<ZReports> optionalZReports = zReportRepo.findById(UUID.fromString(id));
        return optionalZReports.<HttpEntity<ZReports>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/z_reports/{id}")
    HttpEntity<ZReports> deleteZReportsBYId(@PathVariable String id)
    {
        Optional<ZReports> optionalZReports = zReportRepo.findById(UUID.fromString(id));
        if (optionalZReports.isPresent())
        {
            zReportRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/z_reports")
    HttpEntity<ZReports> saveZReports(@RequestBody ZReports zReports)
    {
        if (zReports.getCountChecks()== null)
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            ZReports model=new ZReports(zReports);
            ZReports save = zReportRepo.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }
    }
    @PutMapping("/z_reports/{id}")
    HttpEntity<ZReports> updateZReports(@RequestBody ZReports zReports,@PathVariable String id)
    {
        Optional<ZReports> optionalZReports = zReportRepo.findById(UUID.fromString(id));
        if (!optionalZReports.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            zReports.setId(UUID.fromString(id));
            ZReports save = zReportRepo.save(zReports);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }
    @GetMapping("/z_reports")
    HttpEntity<List<ZReports>> getZReortsList()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(zReportRepo.findAll());
    }

//    @GetMapping("/managements/fullname/{fullName}")
//    HttpEntity<List<Management>> findManagerByFullName(@PathVariable(name = "fullName") String name)
//    {
//        return ResponseEntity.ok(managementService.findManagersByName(name));
//    }
//    @GetMapping("/managements/username/{username}")
//    HttpEntity<Management> findManagerByUsername(@PathVariable(name = "username") String username)
//    {
//        Management managerByUsername = managementService.findManagerByUsername(username);
//
//        if (managerByUsername.getUsername() == null)
//        {
//            return ResponseEntity.notFound().build();
//        }
//        else return ResponseEntity.ok(managerByUsername);
//    }
}
