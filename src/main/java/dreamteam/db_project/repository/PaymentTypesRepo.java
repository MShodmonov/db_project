package dreamteam.db_project.repository;

import dreamteam.db_project.model.PaymentTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentTypesRepo extends JpaRepository<PaymentTypes, UUID> {
}
