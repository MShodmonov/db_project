package dreamteam.db_project.controllers;

import dreamteam.db_project.model.Suppliers;
import dreamteam.db_project.repository.SupplierRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class SuppliersController {


    private final SupplierRepo supplierRepo;

    public SuppliersController(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }


    @GetMapping("/suppliers/{id}")
    public HttpEntity<Suppliers> getSuppliersById(@PathVariable(name = "id") String id)
    {
        Optional<Suppliers> optionalSuppliers = supplierRepo.findById(UUID.fromString(id));
        return optionalSuppliers.<HttpEntity<Suppliers>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/suppliers/{id}")
    HttpEntity<Suppliers> deleteSupplierBYId(@PathVariable String id)
    {
        Optional<Suppliers> optionalSuppliers = supplierRepo.findById(UUID.fromString(id));
        if (optionalSuppliers.isPresent())
        {
            supplierRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/suppliers")
    HttpEntity<Suppliers> saveSuppliers(@RequestBody Suppliers suppliers)
    {
        if (suppliers.getFullName()== null)
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            Suppliers model=new Suppliers(suppliers);
            Suppliers save = supplierRepo.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }
    }
    @PutMapping("/suppliers/{id}")
    HttpEntity<Suppliers> updateSuppliers(@RequestBody Suppliers suppliers,@PathVariable String id)
    {
        Optional<Suppliers> optionalSuppliers = supplierRepo.findById(UUID.fromString(id));
        if (!optionalSuppliers.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            suppliers.setId(UUID.fromString(id));
            Suppliers save = supplierRepo.save(suppliers);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }
    @GetMapping("/suppliers")
    HttpEntity<List<Suppliers>> getManagementList()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierRepo.findAll());
    }

    @GetMapping("/suppliers/fullname/{fullName}")
    HttpEntity<List<Suppliers>>findSupplierByFullName(@PathVariable(name = "fullName") String name)
    {
        List<Suppliers> byFullNameContaining = supplierRepo.findByFullNameContaining(name);
        if (byFullNameContaining.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(byFullNameContaining);
        }
    }
    @GetMapping("/suppliers/company/{company}")
    HttpEntity<List<Suppliers>> findSupplierByCompany(@PathVariable(name = "company") String company)
    {
        List<Suppliers> byCompanyContaining = supplierRepo.findByCompanyContaining(company);

        if (byCompanyContaining.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(byCompanyContaining);
    }
}
