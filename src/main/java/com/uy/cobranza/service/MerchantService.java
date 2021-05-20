package com.uy.cobranza.service;

import com.uy.cobranza.exception.NegocioException;
import com.uy.cobranza.model.Merchant;
import com.uy.cobranza.params.MerchantParams;

import java.util.List;
import java.util.Optional;

public interface MerchantService {

    void addMerchant(MerchantParams merchantParams) throws NegocioException, NegocioException;

    Optional<Merchant> getMerchant(String code);

    List<Merchant> list();

    void delete(Merchant merchant);

}
