package dreamteam.db_project.repository;

import dreamteam.db_project.model.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepo extends JpaRepository<Suppliers, UUID> {
}
