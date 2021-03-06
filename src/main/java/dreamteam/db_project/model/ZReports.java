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
class ZReports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer countChecks;

    private Integer status;

    private Timestamp openDate;

    private Timestamp closeDate;

    @Column(precision = 20,scale = 2)
    private Float totalSaleCash;

    @Column(precision = 20,scale = 2)
    private Float totalSaleCard;

    @Column(precision = 20,scale = 2)
    private Float totalSaleVat;

    public ZReports(ZReports zReports) {
        this.countChecks = zReports.getCountChecks();
        this.status = zReports.getStatus();
        this.openDate = zReports.getOpenDate();
        this.closeDate=zReports.getCloseDate();
        this.totalSaleCash=zReports.getTotalSaleCash();
        this.totalSaleCard=zReports.getTotalSaleCard();
        this.totalSaleVat=zReports.getTotalSaleVat();
    }
}
