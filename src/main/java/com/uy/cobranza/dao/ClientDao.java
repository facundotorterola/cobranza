package com.uy.cobranza.dao;

import com.uy.cobranza.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface  ClientDao  extends JpaRepository<Client, Integer> {

    @Query(value="SELECT count(*) as countOfTransactions, c.type \n" +
            "FROM transaction t  inner join client c  on c.number = t.client_code  \n" +
            "WHERE t.creation_date >= :since AND t.creation_date < :until AND c.number =:clientNumber"
            ,nativeQuery = true)
    ClientTypeCountOfTransactionsDto getTheCountOfTransactionsByClient(@Param("clientNumber")Integer clientNumber, @Param("since")String since, @Param("until")String until );

    public static interface ClientTypeCountOfTransactionsDto {
        Integer getCountOfTransactions();
        String getType();
    }
}
