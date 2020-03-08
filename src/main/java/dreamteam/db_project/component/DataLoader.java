package dreamteam.db_project.component;


import dreamteam.db_project.model.Management;
import dreamteam.db_project.repository.ManagementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {


    private final ManagementRepo managementRepo;

    public DataLoader(ManagementRepo managementRepo) {
        this.managementRepo = managementRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        managementRepo.save(new Management("musobek","musobek","njdbcsakld","bckdjsb"));
        managementRepo.save(new Management("musoek","musobek","njdbakld","bckdjsb"));
        managementRepo.save(new Management("mbek","musobek","njdbcsakld","bcjsb"));
        managementRepo.save(new Management("mubek","mobek","njdbakld","bcjsb"));
        managementRepo.save(new Management("mubek","obek","njdbcsakld","bckdjsb"));
    }
}
