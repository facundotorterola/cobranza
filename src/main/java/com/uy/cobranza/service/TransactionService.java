package com.uy.cobranza.service;

import com.uy.cobranza.exception.NegocioException;
import com.uy.cobranza.model.Processor;
import com.uy.cobranza.model.Transaction;
import com.uy.cobranza.params.ProcessorParams;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> list();

    Optional<Transaction> getTransaction(String code);

    void addTransaction(Transaction transaction) throws NegocioException;


    void deleteTransaction(Transaction transaction);
}
