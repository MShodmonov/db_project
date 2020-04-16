package dreamteam.db_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Length(max = 100)
    String role;

    Boolean desktopAdmin;

    Boolean isMonitoring;

    Boolean userManagement;

    public Roles( String role, Boolean desktopAdmin, Boolean userManagement,Boolean isMonitoring) {
        this.role=role;
        this.desktopAdmin=desktopAdmin;
        this.userManagement=userManagement;
        this.isMonitoring=isMonitoring;
    }

}
