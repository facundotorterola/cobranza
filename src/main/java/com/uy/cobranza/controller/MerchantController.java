package com.uy.cobranza.controller;


import com.uy.cobranza.exception.BusinessException;
import com.uy.cobranza.model.Merchant;
import com.uy.cobranza.params.MerchantParams;
import com.uy.cobranza.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Merchant> list(){
        return merchantService.listMerchants();
    }

    @RequestMapping(value = "/{code}",method = RequestMethod.GET)
    public Optional<Merchant> getMerchant(@PathVariable("code") String code) {
        return merchantService.getMerchant(code);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody MerchantParams merchantParams) throws BusinessException {
        merchantService.addMerchant(merchantParams);
    }
}
