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
public
class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Timestamp date;

    private Float totalCost;

    @ManyToOne
    private Stuffs stuff;

    private Integer isFavourite;


    @OneToOne
    private PaymentTypes paymentType;  //=========>>

    @Column(precision = 20,scale = 2)
    private Float discount;

    @Column(precision = 20,scale = 2)
    private Float cashPayment;

    @Column(precision = 20,scale = 2)
    private Float cardPayment;

    @Column(precision = 20,scale = 2)
    private Float totalVat;



}
