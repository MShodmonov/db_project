package dreamteam.db_project.controllers;
import dreamteam.db_project.model.PriceJournal;
import dreamteam.db_project.repository.PriceJournalRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController()
@RequestMapping("/api")
public class PriceJournalController {

    private final PriceJournalRepo priceJournalRepo;

    public PriceJournalController(PriceJournalRepo priceJournalRepo) {
        this.priceJournalRepo = priceJournalRepo;
    }

    @GetMapping("/price_journals/{id}")
    public HttpEntity<PriceJournal> getPriceJournalById(@PathVariable(name = "id") String id)
    {
        Optional<PriceJournal> optionalPriceJournal = priceJournalRepo.findById(UUID.fromString(id));
        return optionalPriceJournal.<HttpEntity<PriceJournal>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/price_journals/{id}")
    HttpEntity<PriceJournal> deletePriceJournalById(@PathVariable String id)
    {
        Optional<PriceJournal> optionalPriceJournal = priceJournalRepo.findById(UUID.fromString(id));
        if (optionalPriceJournal.isPresent())
        {
            priceJournalRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/price_journals")
    HttpEntity<PriceJournal> savePriceJournal(@RequestBody PriceJournal priceJournal)
    {
        if (priceJournal.getNewPrice()== null)
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            PriceJournal model=new PriceJournal(priceJournal);
            PriceJournal save = priceJournalRepo.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }
    }
    @PutMapping("/price_journals/{id}")
    HttpEntity<PriceJournal> updatePriceJournal(@RequestBody PriceJournal priceJournal,@PathVariable String id)
    {
        Optional<PriceJournal> optionalPriceJournal = priceJournalRepo.findById(UUID.fromString(id));
        if (!optionalPriceJournal.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            priceJournal.setId(UUID.fromString(id));
            PriceJournal save = priceJournalRepo.save(priceJournal);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }
    @GetMapping("/price_journals")
    HttpEntity<List<PriceJournal>> getPriceJournalList()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(priceJournalRepo.findAll());
    }

    @GetMapping("/price_journal/products/{product_id}")
    HttpEntity<PriceJournal> findPriceJournalByProductId(@PathVariable(name = "product_id") String id)
    {
        Optional<PriceJournal> byProducts_id = priceJournalRepo.findByProducts_Id(UUID.fromString(id));
        return byProducts_id.<HttpEntity<PriceJournal>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
