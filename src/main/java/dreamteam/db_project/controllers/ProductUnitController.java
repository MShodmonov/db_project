package dreamteam.db_project.controllers;

import dreamteam.db_project.model.ProductUnits;
import dreamteam.db_project.repository.ProductUnitRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductUnitController {
    private final ProductUnitRepo productUnitRepo;

    public ProductUnitController(ProductUnitRepo productUnitRepo) {
        this.productUnitRepo = productUnitRepo;
    }

    @GetMapping("/product_units/{id}")
    public HttpEntity<ProductUnits> getProductUnitById(@PathVariable(name = "id") String id)
    {
        Optional<ProductUnits> optionalProductUnits = productUnitRepo.findById(UUID.fromString(id));
      return optionalProductUnits.<HttpEntity<ProductUnits>>map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.notFound().build());

    }

    @DeleteMapping("/product_units/{id}")
    HttpEntity<ProductUnits> deleteProductUnitsBYId(@PathVariable String id)
    {
        Optional<ProductUnits> optionalProductUnits = productUnitRepo.findById(UUID.fromString(id));
        if (optionalProductUnits.isPresent())
        {
            productUnitRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/product_units")
    HttpEntity<ProductUnits> saveProductUnits(@RequestBody ProductUnits productUnits)
    {
        if (productUnits.getName()== null)
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            ProductUnits model=new ProductUnits(productUnits);
            ProductUnits save = productUnitRepo.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }
    }
    @PutMapping("/product_units/{id}")
    HttpEntity<ProductUnits> updateProductUnits(@RequestBody ProductUnits productUnits,@PathVariable String id)
    {
        Optional<ProductUnits> optionalProductUnits = productUnitRepo.findById(UUID.fromString(id));
        if (!optionalProductUnits.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            productUnits.setId(UUID.fromString(id));
            ProductUnits save = productUnitRepo.save(productUnits);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }
    @GetMapping("/product_units")
    HttpEntity<List<ProductUnits>> getProductList()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(productUnitRepo.findAll());
    }

    @GetMapping("/managements/name/{name}")
    HttpEntity<List<ProductUnits>> findProductUnitsByName(@PathVariable(name = "name") String name)
    {
        return ResponseEntity.ok(productUnitRepo.findByNameContaining(name));
    }
}
