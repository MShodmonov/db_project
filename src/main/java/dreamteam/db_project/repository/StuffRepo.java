package dreamteam.db_project.repository;

import dreamteam.db_project.model.Stuffs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StuffRepo extends JpaRepository<Stuffs, UUID> {
}
