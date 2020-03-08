package dreamteam.db_project.repository;

import dreamteam.db_project.model.ZReports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ZReportRepo extends JpaRepository<ZReports, UUID> {
}
