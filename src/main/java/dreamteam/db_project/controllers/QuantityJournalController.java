package dreamteam.db_project.controllers;


import dreamteam.db_project.model.QuantityJournal;
import dreamteam.db_project.repository.QuantityJournalRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/api")
public class QuantityJournalController {


    private final QuantityJournalRepo quantityJournalRepo;

    public QuantityJournalController(QuantityJournalRepo quantityJournalRepo) {
        this.quantityJournalRepo = quantityJournalRepo;
    }


    @GetMapping("/quantity_journals/{id}")
    public HttpEntity<QuantityJournal> getQuantityJournalById(@PathVariable(name = "id") String id)
    {
        Optional<QuantityJournal> optionalQuantityJournal = quantityJournalRepo.findById(UUID.fromString(id));
        return optionalQuantityJournal.<HttpEntity<QuantityJournal>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/quantity_journals/{id}")
    HttpEntity<QuantityJournal> deleteQuantityJournalById(@PathVariable String id)
    {
        Optional<QuantityJournal> optionalQuantityJournal = quantityJournalRepo.findById(UUID.fromString(id));
        if (optionalQuantityJournal.isPresent())
        {
            quantityJournalRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/quantity_journals")
    HttpEntity<QuantityJournal> saveQuantityJournal(@RequestBody QuantityJournal quantityJournal)
    {
        if (quantityJournal.getQuantity()== null)
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            QuantityJournal model=new QuantityJournal(quantityJournal);
            QuantityJournal save = quantityJournalRepo.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }
    }
    @PutMapping("/quantity_journals/{id}")
    HttpEntity<QuantityJournal> updateQuantityJournal(@RequestBody QuantityJournal quantityJournal,@PathVariable String id)
    {
        Optional<QuantityJournal> optionalQuantityJournal = quantityJournalRepo.findById(UUID.fromString(id));
        if (!optionalQuantityJournal.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            quantityJournal.setId(UUID.fromString(id));
            QuantityJournal save = quantityJournalRepo.save(quantityJournal);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }
    @GetMapping("/quantity_journals")
    HttpEntity<List<QuantityJournal>> getQuantityJournalList()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(quantityJournalRepo.findAll());
    }

    @GetMapping("/quantity_journals/products/{product_id}")
    HttpEntity<QuantityJournal> findQuantityJournalByProductId(@PathVariable(name = "product_id") String id)
    {
        Optional<QuantityJournal> byProducts_id = quantityJournalRepo.findByProducts_Id(UUID.fromString(id));
        return byProducts_id.<HttpEntity<QuantityJournal>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
