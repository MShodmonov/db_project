package dreamteam.db_project.repository;

import dreamteam.db_project.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepo extends JpaRepository<Products, UUID> {
}
