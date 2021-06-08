package com.uy.cobranza.dao;

import com.uy.cobranza.model.Merchant;
import com.uy.cobranza.model.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface ProcessorDao extends JpaRepository<Processor, String> {

    @Query(value="SELECT p.processor_limit, SUM(t.amount) as sum_amount_transactions from transaction  t Inner Join processor p ON p.code = t.processor_code WHERE p.code= ?1 AND t.creation_date >= ?2 AND t.creation_date < ?3",nativeQuery = true )
    Map<String,Double> getTheSumOfAmountOfTransactionsForAProcessor(String code, String since, String until);
}
