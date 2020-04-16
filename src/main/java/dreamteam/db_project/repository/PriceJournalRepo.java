package dreamteam.db_project.repository;

import dreamteam.db_project.model.PriceJournal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PriceJournalRepo extends JpaRepository<PriceJournal, UUID> {
    Optional<PriceJournal>findByProducts_Id(UUID uuid);
}
