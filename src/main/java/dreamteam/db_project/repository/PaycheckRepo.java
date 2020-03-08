package dreamteam.db_project.repository;

import dreamteam.db_project.model.Paychecks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaycheckRepo extends JpaRepository<Paychecks, UUID> {
}
