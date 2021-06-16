package com.uy.cobranza.service;

import com.uy.cobranza.exception.NegocioException;
import com.uy.cobranza.model.Processor;
import com.uy.cobranza.model.Transaction;
import com.uy.cobranza.params.ProcessorParams;
import com.uy.cobranza.responses.TransactionMerchantReportResponse;
import com.uy.cobranza.responses.TransactionProcessorReportResponse;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> listTransactions();

    List<Transaction> listTransactionOnMonth(Integer monthNumber);

    TransactionMerchantReportResponse listTransactionByMerchant(String merchantCode);


    TransactionProcessorReportResponse listTransactionByProcessor(String processorCode);


    Optional<Transaction> getTransaction(String code);

    void addTransaction(Transaction transaction) throws NegocioException;


    void deleteTransaction(Transaction transaction);
}
