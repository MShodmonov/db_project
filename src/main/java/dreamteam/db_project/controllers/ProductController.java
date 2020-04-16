package dreamteam.db_project.controllers;

import dreamteam.db_project.model.Management;
import dreamteam.db_project.model.Products;
import dreamteam.db_project.repository.ProductRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/api")
public class ProductController {

   private final ProductRepo productRepo;

    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    @GetMapping("/products/{id}")
    public HttpEntity<Products> getProductById(@PathVariable(name = "id") String id)
    {
        Optional<Products> optionalProducts = productRepo.findById(UUID.fromString(id));
        if (optionalProducts.isPresent())
        {
            return ResponseEntity.ok(optionalProducts.get());
        }
        else return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/products/{id}")
    HttpEntity<Products> deleteProductBYId(@PathVariable String id)
    {
        Optional<Products> optionalProducts = productRepo.findById(UUID.fromString(id));
        if (optionalProducts.isPresent())
        {
            productRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/products")
    HttpEntity<Products> saveProducts(@RequestBody Products products)
    {
        if (products.getBarcode()== null)
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            Products model=new Products(products.getProductName(),products.getDescription(),products.getFullName(),products.getBarcode(),products.getQuantity(),products.getExpirationTime(),products.getDiscount(),products.getFavourite(),products.getExtraQuantity(),products.getPurchaseCost(),products.getVat());
            Products save = productRepo.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }
    }
    @PutMapping("/products/{id}")
    HttpEntity<Products> updateProducts(@RequestBody Products products,@PathVariable String id)
    {
        Optional<Products> optionalProducts = productRepo.findById(UUID.fromString(id));
        if (!optionalProducts.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            products.setId(UUID.fromString(id));
            Products save = productRepo.save(products);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }
    @GetMapping("/products")
    HttpEntity<List<Products>> getProductList()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepo.findAll());
    }

    @GetMapping("/products/product_name/{product_name}")
    HttpEntity<List<Products>> findProductByProductName(@PathVariable(name = "productName") String productName)
    {
        return ResponseEntity.ok(productRepo.findByProductNameContaining(productName));
    }
    @GetMapping("/products/barcode/{barcode}")
    HttpEntity<Products> findProductByBarcode(@PathVariable(name = "barcode") String barcode)
    {
        Optional<Products> optionalProducts = productRepo.findByBarcode(barcode);
      return   optionalProducts.<HttpEntity<Products>>map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.notFound().build());
    }

}
