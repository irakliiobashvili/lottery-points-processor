package optimo.lotterypointsprocessor.repository;

import optimo.lotterypointsprocessor.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Irakli Iobashvili
 */
public interface RoundRepository extends JpaRepository<Round, Long>, JpaSpecificationExecutor<Round> {
    @Query(value = """
            select *
            from LOTTERY_DEV.ROUNDS
            where (sysdate between ROUND_START and ROUND_END)
            """, nativeQuery = true)
    Round activeRound();
}
