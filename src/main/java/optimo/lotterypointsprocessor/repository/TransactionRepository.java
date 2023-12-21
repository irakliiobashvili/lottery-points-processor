package optimo.lotterypointsprocessor.repository;

import optimo.lotterypointsprocessor.entity.Round;
import optimo.lotterypointsprocessor.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Irakli Iobashvili
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    @Query(value = """
            select count(ID)
            from LOTTERY_DEV.TRANSACTIONS
            where ACTION_TRANSACTION_ID = :tranId
              and ACTION = :action
            """, nativeQuery = true)
    long findByActionAndActionTransactionId(@Param("action") String action, @Param("tranId") Long tranId);
}
