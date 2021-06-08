package com.uy.cobranza.dao;

import com.uy.cobranza.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface  ClientDao  extends JpaRepository<Client, Integer> {

    @Query(value="SELECT count(*) as number_of_transactions, c.type FROM transaction t  inner join client c  on c.number = t.client_code  WHERE t.creation_date >= ?1 AND t.creation_date < ?2 AND c.number =?3",nativeQuery = true)
    Map<String,Object> getTheCountOfTransactionsByClient(String since, String until , Integer number);
}
