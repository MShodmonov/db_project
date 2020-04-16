package dreamteam.db_project.controllers;

import dreamteam.db_project.model.SoldProducts;
import dreamteam.db_project.repository.SoldProductsRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/api")
public class SoldProductsController {


    private final SoldProductsRepo soldProductsRepo;

    public SoldProductsController(SoldProductsRepo soldProductsRepo) {
        this.soldProductsRepo = soldProductsRepo;
    }


    @GetMapping("/sold_products/{id}")
    public HttpEntity<SoldProducts> getSoldProductsById(@PathVariable(name = "id") String id)
    {
        Optional<SoldProducts> optionalSoldProducts = soldProductsRepo.findById(UUID.fromString(id));
        return optionalSoldProducts.<HttpEntity<SoldProducts>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/sold_products/{id}")
    HttpEntity<SoldProducts> deleteSoldProductsById(@PathVariable String id)
    {
        Optional<SoldProducts> optionalSoldProducts = soldProductsRepo.findById(UUID.fromString(id));
        if (optionalSoldProducts.isPresent())
        {
            soldProductsRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/sold_products")
    HttpEntity<SoldProducts> saveSoldProducts(@RequestBody SoldProducts soldProducts)
    {
        if (soldProducts.getQuantity()== null)
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            SoldProducts model=new SoldProducts(soldProducts);
            SoldProducts save = soldProductsRepo.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }
    }
    @PutMapping("/sold_products/{id}")
    HttpEntity<SoldProducts> updateSoldProducts(@RequestBody SoldProducts soldProducts,@PathVariable String id)
    {
        Optional<SoldProducts> optionalSoldProducts = soldProductsRepo.findById(UUID.fromString(id));
        if (!optionalSoldProducts.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            soldProducts.setId(UUID.fromString(id));
            SoldProducts save = soldProductsRepo.save(soldProducts);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }
    @GetMapping("/sold_products")
    HttpEntity<List<SoldProducts>> getSoldProductsList()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(soldProductsRepo.findAll());
    }

    @GetMapping("/sold_products/products/{product_id}")
    HttpEntity<SoldProducts> findSoldProductsByProductId(@PathVariable(name = "product_id") String id)
    {
        Optional<SoldProducts> byProducts_id = soldProductsRepo.findByProduct_Id(UUID.fromString(id));
        return byProducts_id.<HttpEntity<SoldProducts>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/sold_products/orders/{order_id}")
    HttpEntity<SoldProducts> findSoldProductsByOrderId(@PathVariable(name = "order_id") String id)
    {
        Optional<SoldProducts> byProducts_id = soldProductsRepo.findByOrder_Id(UUID.fromString(id));
        return byProducts_id.<HttpEntity<SoldProducts>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
