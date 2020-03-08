package dreamteam.db_project.repository;

import dreamteam.db_project.model.NewProductJournal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NewProductJournalRepo extends JpaRepository<NewProductJournal, UUID> {
}
