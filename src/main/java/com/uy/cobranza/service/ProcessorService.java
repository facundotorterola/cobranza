package com.uy.cobranza.service;

import com.uy.cobranza.dao.ProcessorDao;
import com.uy.cobranza.exception.BusinessException;
import com.uy.cobranza.model.Processor;
import com.uy.cobranza.params.ProcessorParams;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProcessorService {

    List<Processor> listProcessors();

    List<ProcessorDao.ProcessorDTO> listProcessorsCloseOutOfBalance(Integer delta);

    Optional<Processor> getProcessor(String code);


    Map<String,Double> getTheSumOfAmountOfTransactionsForAProcessor(String processorCode,String sinceDateStr,String untilDateStr);

    void addProcessor(ProcessorParams processorParams) throws BusinessException;


    void deleteProcessor(Processor processor);

}
