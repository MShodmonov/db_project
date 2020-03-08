package dreamteam.db_project.repository;

import dreamteam.db_project.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, UUID> {
}
