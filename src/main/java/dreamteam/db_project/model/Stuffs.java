package dreamteam.db_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public
class Stuffs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Length(max = 255)
    private String fullName;

    @Length(max = 100)
    private String username;

    @Length(max = 100)
    private String password;

    private String bioInfo;

    @OneToOne
    private Roles roles;

    @Length(max = 20)
    private String phoneNumber;

    public Stuffs( String fullName, String username,String password,String bioInfo, Roles roles, String phoneNumber) {

        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.bioInfo = bioInfo;
        this.roles = roles;
        this.phoneNumber = phoneNumber;
    }
}
