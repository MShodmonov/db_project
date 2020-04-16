package dreamteam.db_project.controllers;

import dreamteam.db_project.model.ProductCategory;
import dreamteam.db_project.repository.ProductCategoryRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductCategoryController {

    private final ProductCategoryRepo productCategoryRepo;

    public ProductCategoryController(ProductCategoryRepo productCategoryRepo) {
        this.productCategoryRepo = productCategoryRepo;
    }

    @GetMapping("/product_category/{id}")
    public HttpEntity<ProductCategory> getProductCategoryById(@PathVariable(name = "id") String id)
    {
        Optional<ProductCategory> optionalProductCategory = productCategoryRepo.findById(UUID.fromString(id));
        return optionalProductCategory.<HttpEntity<ProductCategory>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/product_category/{id}")
    HttpEntity<ProductCategory> deleteProductCategoryBYId(@PathVariable String id)
    {
        Optional<ProductCategory> optionalProductCategory = productCategoryRepo.findById(UUID.fromString(id));
        if (optionalProductCategory.isPresent())
        {
            productCategoryRepo.deleteById(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/product_category")
    HttpEntity<ProductCategory> saveProductCategory(@RequestBody ProductCategory productCategory)
    {
        if (productCategory.getName()== null)
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            ProductCategory model=new ProductCategory(productCategory);
            ProductCategory save = productCategoryRepo.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }
    }
    @PutMapping("/product_category/{id}")
    HttpEntity<ProductCategory> updateProductCategory(@RequestBody ProductCategory productCategory,@PathVariable String id)
    {
        Optional<ProductCategory> optionalProductCategory = productCategoryRepo.findById(UUID.fromString(id));
        if (!optionalProductCategory.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            productCategory.setId(UUID.fromString(id));
            ProductCategory save = productCategoryRepo.save(productCategory);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save);
        }
    }
    @GetMapping("/product_category")
    HttpEntity<List<ProductCategory>> getProductCategoryList()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(productCategoryRepo.findAll());
    }

    @GetMapping("/product_category/name/{name}")
    HttpEntity<List<ProductCategory>>findProductCategoryByName(@PathVariable(name = "name") String name)
    {
        List<ProductCategory> byNameContaining = productCategoryRepo.findByNameContaining(name);
        if (byNameContaining.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(byNameContaining);
        }
    }

}
