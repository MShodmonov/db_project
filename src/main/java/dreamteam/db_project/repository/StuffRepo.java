package dreamteam.db_project.repository;

import dreamteam.db_project.model.Management;
import dreamteam.db_project.model.Stuffs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StuffRepo extends JpaRepository<Stuffs, UUID> {
    List<Stuffs> findAllByFullNameContaining(String fullNameLike);

    Optional<Stuffs> findByUsername(String username);
}
