package dreamteam.db_project.component;


import dreamteam.db_project.model.Management;
import dreamteam.db_project.model.Roles;
import dreamteam.db_project.model.Users;
import dreamteam.db_project.repository.ManagementRepo;
import dreamteam.db_project.repository.RolesRepo;
import dreamteam.db_project.repository.UserRepo;
import dreamteam.db_project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {


    private final ManagementRepo managementRepo;
    private final RolesRepo rolesRepo;
    private final UserRepo userRepo;



    public DataLoader(ManagementRepo managementRepo, RolesRepo rolesRepo, UserRepo userRepo) {
        this.managementRepo = managementRepo;
        this.rolesRepo = rolesRepo;
        this.userRepo = userRepo;

    }


    @Override
    public void run(String... args) throws Exception {

        Roles user = rolesRepo.save(new Roles("ROLE_USER", false, false, false));
        Roles admin = rolesRepo.save(new Roles("ROLE_ADMIN", true, true, true));
        Roles manager = rolesRepo.save(new Roles("ROLE_MANAGER", true, true, true));

        List<Roles>adminCollection=new LinkedList<>();
        adminCollection.add(user);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Users save = userRepo.save(new Users("musobek",passwordEncoder.encode("12345"), "musobek", adminCollection));



        managementRepo.save(new Management("musobek","musobek","njdbcsakld","bckdjsb",user));
        managementRepo.save(new Management("musoek","musobek","njdbakld","bckdjsb",user));
        managementRepo.save(new Management("mbek","musobek","njdbcsakld","bcjsb",admin));
        managementRepo.save(new Management("mubek","mobek","njdbakld","bcjsb",manager));
        managementRepo.save(new Management("jurabek","jura","shodmonov","bckdjsb",manager));



    }
}
