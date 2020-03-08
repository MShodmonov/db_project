package dreamteam.db_project.repository;

import dreamteam.db_project.model.ProductUnits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductUnitRepo extends JpaRepository<ProductUnits, UUID> {
}
