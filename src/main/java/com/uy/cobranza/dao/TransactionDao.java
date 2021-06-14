package com.uy.cobranza.dao;


import com.uy.cobranza.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface TransactionDao extends JpaRepository<Transaction, String> {

    @Query(value="SELECT * FROM transaction t \n" +
            "WHERE MONTH(t.creation_date) = ?1",nativeQuery = true )
    List<Transaction> listTransactionsOnMonth(Integer month);


    @Query(value="SELECT * FROM transaction t \n" +
            "WHERE merchant_code =:merchantCode ",nativeQuery = true )
    List<Transaction> listTransactionsByMerchant(@Param("merchantCode") String merchantCode);





    @Query(value = "SELECT count(*) as  countOfTransactions , SUM(t.amount ) as sumOfAmountTransaction,(\n" +
            "\tSELECT  SUM( t.amount *  (m.company_fee/100) )\n" +
            "\tFROM  `transaction` t inner join merchant m on m.code = merchant_code INNER JOIN  client c ON c.`number` =t.client_code \n" +
            "\tWHERE c.`type` ='COMPANY' AND  t.merchant_code =:merchantCode \n" +
            "\t) as sumOfCommissionCompanies,\n" +
            "\t\n" +
            "\t(\n" +
            "\tSELECT  SUM( t.amount *  (m.person_fee /100) )\n" +
            "\tFROM  `transaction` t inner join merchant m on m.code = merchant_code INNER JOIN  client c ON c.`number` =t.client_code \n" +
            "\tWHERE c.`type` = 'PERSON' AND  t.merchant_code =:merchantCode \n" +
            "\t) as sumOfCommissionPerson\n" +
            "\t\n" +
            "FROM `transaction` t inner join merchant m on m.code = merchant_code INNER JOIN  client c ON c.`number` =t.client_code \n" +
            "WHERE t.merchant_code = :merchantCode", nativeQuery = true)
    TransactionReportDTO getNumberTransactionAndMerchantCommission(@Param("merchantCode") String merchantCode);




    @Query(value="SELECT * FROM transaction t \n" +
            "WHERE processor_code =:processorCode ",nativeQuery = true )
    List<Transaction> listTransactionsByProcessor(@Param("processorCode") String processorCode);

    @Query(value = "SELECT count(*) as  countOfTransactions , SUM(t.amount ) as sumOfAmountTransaction,(\n" +
            "\tSELECT  SUM( t.amount *  (m.company_fee/100) )\n" +
            "\tFROM  `transaction` t inner join merchant m on m.code = merchant_code INNER JOIN  client c ON c.`number` =t.client_code \n" +
            "\tWHERE c.`type` ='COMPANY' AND  t.processor_code =:processorCode \n" +
            "\t) as sumOfCommissionCompanies,\n" +
            "\t\n" +
            "\t(\n" +
            "\tSELECT  SUM( t.amount *  (m.person_fee /100) )\n" +
            "\tFROM  `transaction` t inner join merchant m on m.code = merchant_code INNER JOIN  client c ON c.`number` =t.client_code \n" +
            "\tWHERE c.`type` = 'PERSON' AND  t.processor_code =:processorCode \n" +
            "\t) as sumOfCommissionPerson\n" +
            "\t\n" +
            "FROM `transaction` t inner join merchant m on m.code = merchant_code INNER JOIN  client c ON c.`number` =t.client_code \n" +
            "WHERE  t.processor_code =:processorCode", nativeQuery = true)
    TransactionReportDTO getNumberTransactionAndProcessorCommission(@Param("processorCode") String processorCode);


    public static interface TransactionReportDTO {
        Integer getCountOfTransactions();
        Double getSumOfAmountTransaction();
        Double getSumOfCommissionPerson();
        Double getSumOfCommissionCompanies();

    }

}
