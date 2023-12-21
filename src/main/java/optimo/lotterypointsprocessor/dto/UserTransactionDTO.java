package optimo.lotterypointsprocessor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Irakli Iobashvili
 */

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTransactionDTO {
    private String userId;
    private Double pointAmount;
    private Timestamp transactionDate;
    private Long status;
    private Long transactionType;
    private Long transactionId;
}
