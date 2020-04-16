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
public class PriceJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    private Products products;

    @Column(precision = 20,scale = 2)
    private Float oldPrice; //=========>

    @Column(precision = 20,scale = 2)
    private Float newPrice; //========>

    private Timestamp editDate; ///========>

    private Boolean isIncreased;

    public PriceJournal(PriceJournal priceJournal) {
        this.oldPrice=priceJournal.getOldPrice();
        this.newPrice=priceJournal.getNewPrice();
        this.editDate=priceJournal.getEditDate();
        this.isIncreased=priceJournal.getIsIncreased();
    }
}
