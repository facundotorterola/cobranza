package com.uy.cobranza.service;

import com.uy.cobranza.dao.ClientDao;
import com.uy.cobranza.dao.TransactionDao;
import com.uy.cobranza.exception.BusinessException;
import com.uy.cobranza.model.Client;
import com.uy.cobranza.model.Merchant;
import com.uy.cobranza.model.Processor;
import com.uy.cobranza.model.Transaction;
import com.uy.cobranza.responses.TransactionMerchantReportResponse;
import com.uy.cobranza.responses.TransactionProcessorReportResponse;
import com.uy.cobranza.utils.Constants;
import com.uy.cobranza.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements  TransactionService {


    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private ProcessorService processorService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MerchantService merchantService;


    @Override
    public List<Transaction> listTransactions() {
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
    public void addTransaction(Transaction transaction) throws BusinessException {
        if (transaction.getCreationDate() == null){
            transaction.setCreationDate(new Date());
        }

        Date today = new Date();
        Date tomorrow = DateUtils.getTomorrow();
        String todayStr = DateUtils.formatDateWithoutHour(today);
        String tomorrowStr = DateUtils.formatDateWithoutHour(tomorrow);


        Map<String,Double> resultSet = processorService.getTheSumOfAmountOfTransactionsForAProcessor(transaction.getProcessorCode(),todayStr,tomorrowStr);
        if (resultSet.get("processor_limit")!= null && resultSet.get("sum_amount_transactions")!= null){
            if (resultSet.get("processor_limit") < (resultSet.get("sum_amount_transactions") + transaction.getAmount())){
                throw  new BusinessException("El procesador excedera su limite de proceso diario");
            }

        }else {
            Optional<Processor> processor = processorService.getProcessor(transaction.getProcessorCode());
            if (processor.isEmpty()) {
                throw new BusinessException("El procesador no existe");
            }
            Double processorLimit = processor.get().getLimit();
            if (processorLimit < transaction.getAmount()) {
                throw new BusinessException("El monto de la transaccion excede el limite diario");
            }
        }



        ClientDao.ClientTypeCountOfTransactionsDto countClientTransactions = clientService.getTheCountOfTransactionsByClient(transaction.getClientCode(),todayStr,tomorrowStr);
        if (countClientTransactions.getType()!= null && countClientTransactions.getCountOfTransactions()!=null){
            String type =  countClientTransactions.getType();
            Integer count =  countClientTransactions.getCountOfTransactions();


            if (type =="PERSON"){
                if (count >= Constants.MAX_NUMBER_OF_TRANSACTIONS_PER_PERSON_DAY){
                    throw  new BusinessException("El cliente excede la cantidad de transacciones diarias");
                }
            }else{
                if (count >= Constants.MAX_NUMBER_OF_TRANSACTIONS_PER_COMPANY_DAY){
                    throw  new BusinessException("El cliente excede la cantidad de transacciones diarias");
                }
            }
        }


        Optional<Merchant> merchantOp = merchantService.getMerchant(transaction.getMerchantCode());
        if (merchantOp.isEmpty()){
            throw  new BusinessException("El Merchant con codigo " +transaction.getMerchantCode()+ " no esta en el sistema");
        }else{
            Optional<Client> clientOp = clientService.getClient(transaction.getClientCode());
            if (clientOp.isEmpty()){
                throw  new BusinessException("El Cliente con numero " +transaction.getClientCode()+ " no esta en el sistema");
            }
            List<String> countriesIso = merchantOp.get().getCountries().stream().map(country -> country.getIsoCode()).collect(Collectors.toList());
            if (!countriesIso.contains(clientOp.get().getCountryIso())){
                throw  new BusinessException("El Merchant y el Cliente no operan en el mismo pais ");
            }


            Optional<Processor> processorOp = processorService.getProcessor(transaction.getProcessorCode());
            if(processorOp.isEmpty()){
                throw  new BusinessException("El Processor con codigo " +transaction.getProcessorCode()+ " no esta en el sistema");
            }else{
                if (!processorOp.get().getMerchants().contains(merchantOp.get())){
                    throw  new BusinessException("El Procesador " + transaction.getProcessorCode()+" no trabaja con el Merchant "+transaction.getMerchantCode());

                }
            }
        }

        transactionDao.save(transaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {

    }
}
