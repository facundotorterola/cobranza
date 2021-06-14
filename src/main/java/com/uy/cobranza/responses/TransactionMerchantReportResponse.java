package com.uy.cobranza.responses;

import com.uy.cobranza.model.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class TransactionMerchantReportResponse {
    private List<Transaction> transactions;

    private Integer countOfTransactions;

    private Double sumOfAmountTransaction;

    private Double sumOfCommissionMerchants;

}
