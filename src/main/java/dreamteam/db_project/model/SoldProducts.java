package dreamteam.db_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoldProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    private Orders order;

    @ManyToOne
    private Products product;

    @Column(precision = 10,scale = 2)
    private Float quantity;

    @Column(precision = 20,scale = 2)
    private Float cost;

    public SoldProducts(SoldProducts soldProducts) {
        this.quantity=soldProducts.getQuantity();
        this.cost=soldProducts.getCost();
    }
}
