package optimo.lotterypointsprocessor.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import optimo.lotterypointsprocessor.commons.Constants;
import optimo.lotterypointsprocessor.commons.enums.AccountTypes;
import optimo.lotterypointsprocessor.commons.enums.Actions;
import optimo.lotterypointsprocessor.commons.enums.TransactionTypes;
import optimo.lotterypointsprocessor.dto.UserTransactionDTO;
import optimo.lotterypointsprocessor.entity.Round;
import optimo.lotterypointsprocessor.entity.Transaction;
import optimo.lotterypointsprocessor.repository.RoundRepository;
import optimo.lotterypointsprocessor.repository.TransactionRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * @author Irakli Iobashvili
 */

@Service
@Slf4j
@Transactional
public class UserTransactionConsumerService {
    private final ObjectMapper objectMapper;
    private final KafkaTemplate kafkaTemplate;

    private final RoundRepository roundRepository;
    private final TransactionRepository transactionRepository;

    public UserTransactionConsumerService(ObjectMapper objectMapper,
                                          KafkaTemplate kafkaTemplate,
                                          RoundRepository roundRepository,
                                          TransactionRepository transactionRepository) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.roundRepository = roundRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * function receive message about user point transaction ,
     * message contains transactionId which is unique identifier
     * We can use the transaction ID to avoid handling the same information
     *
     * @param message info about userTransaction
     */
    @KafkaListener(topics = Constants.USER_TRANSACTIONS_TOPIC, groupId = "UserTransactions")
    public void handleUserTransactionEvent(String message) {
        log.info("start handling event %s".formatted(message));
        try {
            UserTransactionDTO genericTransactionTypeDtoForGroupValidation =
                    objectMapper.readValue(message, UserTransactionDTO.class);

            if (genericTransactionTypeDtoForGroupValidation.getPointAmount() > 0) {
                //Active Round
                Round activeRound = roundRepository.activeRound();
                //find Transaction if exist record by action and action_tran_id (if exist does not make transaction)
                long tranByActionAndActionTranId = transactionRepository.findByActionAndActionTransactionId
                        (Actions.POINTS.name(), genericTransactionTypeDtoForGroupValidation.getTransactionId());

                if (tranByActionAndActionTranId == 0) {
                    //collect transaction DTO and save in DB
                    Transaction transaction = new Transaction();
                    transaction.setTType(TransactionTypes.ADD_POINTS.name());
                    transaction.setEntryDate(new Timestamp(System.currentTimeMillis()));
                    transaction.setAmount(genericTransactionTypeDtoForGroupValidation.getPointAmount());
                    transaction.setIp("IP");
                    transaction.setComment("ADD_USER_POINTS");
                    transaction.setUserId(genericTransactionTypeDtoForGroupValidation.getUserId());
                    transaction.setRoundId(activeRound.getId());
                    transaction.setStatus(true);
                    transaction.setProcessed(true);
                    transaction.setFinishDate(new Timestamp(System.currentTimeMillis()));
                    transaction.setAccountType(AccountTypes.POINTS.name());
                    transaction.setAction(Actions.POINTS.name());
                    transaction.setActionTransactionId(genericTransactionTypeDtoForGroupValidation.getTransactionId());
                    transactionRepository.save(transaction);

                    //throw call in kafka pont_processed topic
                    kafkaTemplate.send(Constants.POINT_PROCESSED, objectMapper.writeValueAsString(transaction));
                }
            }
        } catch (Exception e) {
            log.error("Error while handle userTransactionEvent {}", message);
            log.info("sending failed event %s to DLQ ".formatted(message));
            kafkaTemplate.send(Constants.USER_FAILED_TRANSACTIONS_TOPIC, message);
            log.info("successfully send failed event %s".formatted(message));
        }

    }
}
