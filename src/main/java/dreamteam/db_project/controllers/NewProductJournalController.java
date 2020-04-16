package dreamteam.db_project.controllers;


import dreamteam.db_project.model.NewProductJournal;
import dreamteam.db_project.repository.NewProductJournalRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class NewProductJournalController {


    private final NewProductJournalRepo newProductJournalRepo;

    public NewProductJournalController(NewProductJournalRepo newProductJournalRepo) {
        this.newProductJournalRepo = newProductJournalRepo;
    }

    @GetMapping("/new_product_journal/{id}")
    public HttpEntity<NewProductJournal> getNewProductJournalById(@PathVariable String id)
    {
        Optional<NewProductJournal> optionalNewProductJournal = newProductJournalRepo.findById(UUID.fromString(id));
        if (optionalNewProductJournal.isPresent())
        {
            return ResponseEntity.ok(optionalNewProductJournal.get());
        }
        else return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/new_product_journal/{id}")
    HttpEntity<NewProductJournal> deleteNewProductJournalBYId(@PathVariable String id)
    {
        Optional<NewProductJournal> optionalNewProductJournal = newProductJournalRepo.findById(UUID.fromString(id));
        if (optionalNewProductJournal.isPresent())
        {
            newProductJournalRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/new_product_journal")
    HttpEntity<NewProductJournal> saveNewProductJournal(@RequestBody NewProductJournal newProductJournal)
    {
        if (newProductJournal.getId()== null)
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            NewProductJournal save = newProductJournalRepo.save(newProductJournal);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }
    }
    @PutMapping("/new_product_journal/{id}")
    HttpEntity<NewProductJournal> updateManagement(@RequestBody NewProductJournal newProductJournal,@PathVariable String id)
    {
        Optional<NewProductJournal> optionalNewProductJournal = newProductJournalRepo.findById(UUID.fromString(id));
        if (!optionalNewProductJournal.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            newProductJournal.setId(UUID.fromString(id));
            NewProductJournal save = newProductJournalRepo.save(newProductJournal);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }
    @GetMapping("/new_product_journal")
    HttpEntity<List<NewProductJournal>> getNewProductJournalList()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(newProductJournalRepo.findAll());
    }
}
