package dreamteam.db_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Management {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
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

    public Management(String fullName, String username, String password, String bioInfo) {
        this.fullName=fullName;
        this.username=username;
        this.password=password;
        this.bioInfo=bioInfo;
    }
    public Management(String fullName, String username, String password, String bioInfo,Roles roles) {
        this.fullName=fullName;
        this.username=username;
        this.password=password;
        this.bioInfo=bioInfo;
        this.roles=roles;
    }
}
