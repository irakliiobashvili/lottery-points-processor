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
@Table(name = "ROUNDS", schema = "LOTTERY_DEV")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Round {
    @Id
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "ENTRY_DATE")
    private Timestamp entryDate;
    @Basic
    @Column(name = "ROUND_START")
    private Timestamp roundStart;
    @Basic
    @Column(name = "ROUND_END")
    private Timestamp roundEnd;
    @Basic
    @Column(name = "REG_START")
    private Timestamp regStart;
    @Basic
    @Column(name = "REG_END")
    private Timestamp regEnd;
    @Basic
    @Column(name = "JACKPOT_NUMBER")
    private Long jackpotNumber;
    @Basic
    @Column(name = "JACKPOT_AMOUNT")
    private Double jackpotAmount;
    @Basic
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Basic
    @Column(name = "PRE_FINISHED")
    private Boolean refFinished;
    @Basic
    @Column(name = "FINISHED")
    private Boolean finished;
    @Basic
    @Column(name = "RULE_ID")
    private Boolean ruleId;
}
