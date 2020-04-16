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

    public Products(Products products,ProductCategory productCategory, Suppliers suppliers,ProductUnits productUnits) {
    }

    public Products(@Length(max = 255) String productName, String description, Float fullName, @Length(max = 100) String barcode, Float quantity, Timestamp expirationTime, Integer discount, Integer favourite, Float extraQuantity, Float purchaseCost, Integer vat) {
        this.productName = productName;
        this.description = description;
        this.fullName = fullName;
        this.barcode = barcode;
        this.quantity = quantity;
        this.expirationTime = expirationTime;
        this.discount = discount;
        this.favourite = favourite;
        this.extraQuantity = extraQuantity;
        this.purchaseCost = purchaseCost;
        this.vat = vat;
    }
}
