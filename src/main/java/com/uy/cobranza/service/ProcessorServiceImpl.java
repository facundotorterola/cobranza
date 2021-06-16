package com.uy.cobranza.service;

import com.uy.cobranza.dao.ProcessorDao;
import com.uy.cobranza.exception.BusinessException;
import com.uy.cobranza.model.Merchant;
import com.uy.cobranza.model.Processor;
import com.uy.cobranza.params.ProcessorParams;
import com.uy.cobranza.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ProcessorServiceImpl  implements  ProcessorService{

    @Autowired
    ProcessorDao processorDao;


    @Autowired
    MerchantService merchantService;

    @Override
    public List<Processor> listProcessors() {
        return processorDao.findAll();
    }

    @Override
    public List<ProcessorDao.ProcessorDTO> listProcessorsCloseOutOfBalance(Integer delta) {
        Date today = new Date();
        String todayStr = DateUtils.formatDateWithoutHour(today);
        Date tomorrow = DateUtils.getTomorrow();
        String tomorrowStr= DateUtils.formatDateWithoutHour(tomorrow);

        return processorDao.getProcessorsCloseToRunningOutOfBalance(todayStr,tomorrowStr,delta);

    }



    @Override
    public Optional<Processor> getProcessor(String code) {
        return processorDao.findById(code);
    }

    @Override
    public Map<String, Double> getTheSumOfAmountOfTransactionsForAProcessor(String processorCode, String sinceDateStr, String untilDateStr) {
        return processorDao.getTheSumOfAmountOfTransactionsForAProcessor(processorCode,sinceDateStr,untilDateStr);
    }


    @Override
    @Transactional
    public void addProcessor(ProcessorParams processorParams) throws BusinessException {
        Processor processor = new Processor();
        processor.setCode(processorParams.getCode());
        processor.setName(processorParams.getName());
        processor.setLimit(processorParams.getLimit());

        List<Merchant> merchantList = new ArrayList<>();

        for (String merchantCode: processorParams.getMerchantCodes()) {
            Optional<Merchant> merchant = merchantService.getMerchant(merchantCode);
            if (merchant.isEmpty()){
                throw  new BusinessException("El merchant con code "+ merchantCode +" no existe");
            }
            merchantList.add(merchant.get());
        }

        processor.setMerchants(merchantList);

        processorDao.save(processor);
    }




    @Override
    public void deleteProcessor(Processor processor) {
        processorDao.delete(processor);
    }
}
