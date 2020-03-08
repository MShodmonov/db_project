package dreamteam.db_project.repository;

import dreamteam.db_project.model.Management;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ManagementRepo extends JpaRepository<Management, UUID> {
    List<Management> findAllByFullNameContaining(String fullNameLike);

    Optional<Management> findByUsername(String username);
}
