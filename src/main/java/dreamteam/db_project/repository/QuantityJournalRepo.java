package dreamteam.db_project.repository;

import dreamteam.db_project.model.QuantityJournal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuantityJournalRepo extends JpaRepository<QuantityJournal, UUID> {
}
