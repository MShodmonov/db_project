package dreamteam.db_project.repository;

import dreamteam.db_project.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrdersRepo extends JpaRepository<Orders, UUID> {
}
