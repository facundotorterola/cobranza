package com.uy.cobranza.controller;


import com.uy.cobranza.dao.ProcessorDao;
import com.uy.cobranza.exception.NegocioException;
import com.uy.cobranza.model.Processor;
import com.uy.cobranza.params.ProcessorParams;
import com.uy.cobranza.service.ProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/processors")
public class ProcessorController {

    @Autowired
    private ProcessorService processorService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Processor> list(){
        return processorService.list();
    }

    @RequestMapping(value = "/{code}",method = RequestMethod.GET)
    public Optional<Processor> getProcessor(@PathVariable("code") String code) {
        return processorService.getProcessor(code);

    }



    @RequestMapping(value = "/closeOutOfBalance/{delta}",method = RequestMethod.GET)
    public  List<ProcessorDao.ProcessorDTO>  listProcessorsCloseOutOfBalance(@PathVariable("delta") Integer delta) {
        return processorService.listProcessorsCloseOutOfBalance(delta);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody ProcessorParams processorParams) throws NegocioException {
        processorService.addProcessor(processorParams);
    }
}
