package dreamteam.db_project.repository;

import dreamteam.db_project.model.SoldProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SoldProductsRepo extends JpaRepository<SoldProducts, UUID> {
}
