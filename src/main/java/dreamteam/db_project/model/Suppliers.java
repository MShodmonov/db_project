package dreamteam.db_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Length(max = 255)
    private String fullName;

    @Length(max = 20)
    private String phoneNumber;

    @Length(max = 100)
    private String company;

    public Suppliers(Suppliers suppliers) {
        this.fullName=suppliers.getFullName();
        this.phoneNumber=suppliers.getPhoneNumber();
        this.company=suppliers.getCompany();
    }
}
