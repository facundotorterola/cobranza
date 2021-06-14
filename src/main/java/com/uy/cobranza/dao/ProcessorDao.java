package com.uy.cobranza.dao;

import com.uy.cobranza.model.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProcessorDao extends JpaRepository<Processor, String> {

    @Query(value="SELECT p.processor_limit, SUM(t.amount) as sum_amount_transactions from transaction  t Inner Join processor p ON p.code = t.processor_code WHERE p.code= ?1 AND t.creation_date >= ?2 AND t.creation_date < ?3",nativeQuery = true )
    Map<String,Double> getTheSumOfAmountOfTransactionsForAProcessor(String code, String since, String until);

    @Query(value="SELECT p.* , SUM(t.amount ) as sum_amount_transactions ,(p.processor_limit -SUM(t.amount)) as today_limit\n" +
            "FROM processor p inner join transaction t on t.processor_code =p.code \n" +
            "WHERE t.creation_date >= ?1 AND t.creation_date < ?2\n" +
            "GROUP  BY p.code ,p.name ,p.processor_limit \n" +
            "HAVING (p.processor_limit -SUM(t.amount ))  < ?3\n" +
            "ORDER BY today_limit asc",nativeQuery = true )
    List<ProcessorDTO> getProcessorsCloseToRunningOutOfBalance(String since ,String until, Integer delta);


    public static interface ProcessorDTO {
        String getCode();
        Double getProcessor_limit();
        String getName();
        Double getSum_amount_transactions();
        Double getToday_limit();
    }

}
