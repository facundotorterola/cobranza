package com.uy.cobranza.service;

import com.uy.cobranza.dao.ClientDao;
import com.uy.cobranza.dao.ProcessorDao;
import com.uy.cobranza.dao.TransactionDao;
import com.uy.cobranza.exception.NegocioException;
import com.uy.cobranza.model.Processor;
import com.uy.cobranza.model.Transaction;
import com.uy.cobranza.responses.TransactionMerchantReportResponse;
import com.uy.cobranza.responses.TransactionProcessorReportResponse;
import com.uy.cobranza.utils.Constants;
import com.uy.cobranza.utils.DateUtils;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements  TransactionService {


    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private ProcessorDao processorDao;

    @Autowired
    private ClientDao clientDao;


    @Override
    public List<Transaction> list() {
        return transactionDao.findAll();
    }

    @Override
    public List<Transaction> listTransactionOnMonth(Integer monthNumber) {
        return transactionDao.listTransactionsOnMonth(monthNumber);
    }

    @Override
    public TransactionMerchantReportResponse listTransactionByMerchant(String merchantCode) {
        TransactionMerchantReportResponse response = new TransactionMerchantReportResponse();
        response.setTransactions(transactionDao.listTransactionsByMerchant(merchantCode));

        TransactionDao.TransactionReportDTO reportMerchantDTO = transactionDao.getNumberTransactionAndMerchantCommission(merchantCode);

        response.setCountOfTransactions(reportMerchantDTO.getCountOfTransactions());
        response.setSumOfAmountTransaction(reportMerchantDTO.getSumOfAmountTransaction());
        Double commissionMerchant = reportMerchantDTO.getSumOfCommissionPerson() == null ? 0 : reportMerchantDTO.getSumOfCommissionPerson();
        commissionMerchant+= reportMerchantDTO.getSumOfCommissionCompanies() == null ? 0 : reportMerchantDTO.getSumOfCommissionCompanies();
        response.setSumOfCommissionMerchants(commissionMerchant);

         return response;
    }

    @Override
    public TransactionProcessorReportResponse listTransactionByProcessor(String processorCode) {
        TransactionProcessorReportResponse response = new TransactionProcessorReportResponse();

        response.setTransactions(transactionDao.listTransactionsByProcessor(processorCode));

        TransactionDao.TransactionReportDTO reportMerchantDTO = transactionDao.getNumberTransactionAndProcessorCommission(processorCode);

        response.setCountOfTransactions(reportMerchantDTO.getCountOfTransactions());
        response.setSumOfAmountTransaction(reportMerchantDTO.getSumOfAmountTransaction());
        Double commissionProcessor = reportMerchantDTO.getSumOfCommissionPerson() == null ? 0 : reportMerchantDTO.getSumOfCommissionPerson();
        commissionProcessor+= reportMerchantDTO.getSumOfCommissionCompanies() == null ? 0 : reportMerchantDTO.getSumOfCommissionCompanies();
        response.setSumOfCommissionProcessors(commissionProcessor);

        return response;

    }


    @Override
    public Optional<Transaction> getTransaction(String code) {
        return transactionDao.findById(code);
    }



    @Override
    public void addTransaction(Transaction transaction) throws NegocioException {
        if (transaction.getCreationDate() == null){
            transaction.setCreationDate(new Date());
        }

        Date today = new Date();
        Date tomorrow = DateUtils.getTomorrow();
        String todayStr = DateUtils.formatDateWithoutHour(today);
        String tomorrowStr = DateUtils.formatDateWithoutHour(tomorrow);


        Map<String,Double> resultSet = processorDao.getTheSumOfAmountOfTransactionsForAProcessor(transaction.getProcessorCode(),todayStr,tomorrowStr);
        if (resultSet.get("processor_limit")!= null && resultSet.get("sum_amount_transactions")!= null){
            if (resultSet.get("processor_limit") < (resultSet.get("sum_amount_transactions") + transaction.getAmount())){
                throw  new NegocioException("El procesador excedera su limite de proceso diario");
            }

        }else {
            Optional<Processor> processor = processorDao.findById(transaction.getProcessorCode());
            if (processor.isEmpty()) {
                throw new NegocioException("El procesador no existe");
            }
            Double processorLimit = processor.get().getLimit();
            if (processorLimit < transaction.getAmount()) {
                throw new NegocioException("El monto de la transaccion excede el limite diario");
            }
        }



        Map<String,Object> countClientTransactions = clientDao.getTheCountOfTransactionsByClient(todayStr,tomorrowStr,transaction.getClientCode());
        if (countClientTransactions.get("type")!= null && countClientTransactions.get("number_of_transactions")!=null){
            String type = (String) countClientTransactions.get("type");
            BigInteger count = (BigInteger) countClientTransactions.get("number_of_transactions");


            if (type =="PERSON"){
                if (count.intValue() >= Constants.MAX_NUMBER_OF_TRANSACTIONS_PER_PERSON_DAY){
                    throw  new NegocioException("El cliente excede la cantidad de transacciones diarias");
                }
            }else{
                if (count.intValue() >= Constants.MAX_NUMBER_OF_TRANSACTIONS_PER_COMPANY_DAY){
                    throw  new NegocioException("El cliente excede la cantidad de transacciones diarias");
                }
            }
        }


        transactionDao.save(transaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {

    }
}
