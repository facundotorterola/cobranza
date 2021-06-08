package com.uy.cobranza.service;

import com.uy.cobranza.exception.NegocioException;
import com.uy.cobranza.model.Processor;
import com.uy.cobranza.params.ProcessorParams;

import java.util.List;
import java.util.Optional;

public interface ProcessorService {

    List<Processor> list();

    Optional<Processor> getProcessor(String code);

    void addProcessor(ProcessorParams processorParams) throws NegocioException;


    void deleteProcessor(Processor processor);

}
