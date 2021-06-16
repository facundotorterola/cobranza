package com.uy.cobranza.dao;

import com.uy.cobranza.model.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ProcessorDao extends JpaRepository<Processor, String> {

    @Query(value="SELECT p.processor_limit, SUM(t.amount) as sum_amount_transactions \n" +
            "FROM transaction  t Inner Join processor p ON p.code = t.processor_code \n" +
            "WHERE p.code= :processorCode AND t.creation_date >= :since AND t.creation_date < :until"
            ,nativeQuery = true )
    Map<String,Double> getTheSumOfAmountOfTransactionsForAProcessor(@Param("processorCode") String code, @Param("since") String since, @Param("until") String until);

    @Query(value="SELECT p.* , SUM(t.amount ) as sum_amount_transactions ,(p.processor_limit -SUM(t.amount)) as today_limit\n" +
            "FROM processor p inner join transaction t on t.processor_code =p.code \n" +
            "WHERE t.creation_date >= :since AND t.creation_date < :until\n" +
            "GROUP  BY p.code ,p.name ,p.processor_limit \n" +
            "HAVING (p.processor_limit -SUM(t.amount ))  < :delta\n" +
            "ORDER BY today_limit asc",nativeQuery = true )
    List<ProcessorDTO> getProcessorsCloseToRunningOutOfBalance( @Param("since") String since, @Param("until") String until, @Param("delta") Integer delta);



    public static interface ProcessorDTO {
        String getCode();
        Double getProcessor_limit();
        String getName();
        Double getSum_amount_transactions();
        Double getToday_limit();
    }

}
