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
public class Paychecks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Orders order;

    @ManyToOne
    private ZReports report;


    private String qrCodeUrl;

    private String terminalId;

    private String ReceiptSeq;

    private Timestamp dateTime;

    private Timestamp addTime;




}
