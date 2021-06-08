package com.uy.cobranza.controller;


import com.uy.cobranza.exception.NegocioException;
import com.uy.cobranza.model.Client;
import com.uy.cobranza.model.Transaction;
import com.uy.cobranza.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Transaction> list() {

        return transactionService.list();
    }


    @RequestMapping(value = "/{code}",method = RequestMethod.GET)
    public Optional<Transaction> getTransaction(@PathVariable("code") String code) {
        return transactionService.getTransaction(code);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Transaction transaction) throws NegocioException {
        transactionService.addTransaction(transaction);
    }
}
