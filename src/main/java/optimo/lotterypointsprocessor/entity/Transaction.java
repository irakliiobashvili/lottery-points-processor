package optimo.lotterypointsprocessor.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Irakli Iobashvili
 */
@Getter
@Setter
@Entity
@Slf4j
@Table(name = "TRANSACTIONS", schema = "LOTTERY_DEV")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "T_TYPE")
    private String tType;
    @Basic
    @Column(name = "ENTRY_DATE")
    private Timestamp entryDate;
    @Basic
    @Column(name = "AMOUNT")
    private Double amount;
    @Basic
    @Column(name = "IP")
    private String ip;
    @Basic
    @Column(name = "COMMENT_")
    private String comment;
    @Basic
    @Column(name = "USER_ID")
    private String userId;
    @Basic
    @Column(name = "ROUND_ID")
    private Long roundId;
    @Basic
    @Column(name = "STATUS")
    private Boolean status;
    @Basic
    @Column(name = "PROCESSED")
    private Boolean processed;
    @Basic
    @Column(name = "FINISH_DATE")
    private Timestamp finishDate;
    @Basic
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;
    @Basic
    @Column(name = "ACTION")
    private String action;
    @Basic
    @Column(name = "ACTION_TRANSACTION_ID")
    private Long actionTransactionId;
}
