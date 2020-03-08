package dreamteam.db_project.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import java.sql.Timestamp;
import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public
class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Length(max = 255)
    private String productName;


    private String description;

    @Column(precision = 20,scale = 2)
    private Float fullName;

    @Length(max = 100)
    private String barcode;

    @OneToOne
    private ProductCategory productCategory;

    @Column(precision = 10,scale = 2)
    private Float quantity;  // ==========>


    private Timestamp expirationTime;   //=========>

    @OneToOne
    private Suppliers suppliers;

    private Integer discount;

    private Integer favourite;

    @Column(precision = 10,scale = 2)
    private Float extraQuantity;

    @OneToOne
    private ProductUnits productUnits;

    @Column(precision = 20,scale = 2)
    private Float purchaseCost;

    private Integer vat;


}
