package com.uy.cobranza.dao;


import com.uy.cobranza.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<Transaction, String> {


}
