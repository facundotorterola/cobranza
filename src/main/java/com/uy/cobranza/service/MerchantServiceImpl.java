package com.uy.cobranza.service;

import com.uy.cobranza.dao.CountryDao;
import com.uy.cobranza.dao.MerchantDao;
import com.uy.cobranza.exception.NegocioException;
import com.uy.cobranza.model.Country;
import com.uy.cobranza.model.Merchant;
import com.uy.cobranza.params.MerchantParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements  MerchantService {

    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private CountryDao countryDao;

    @Override
    public void addMerchant(MerchantParams merchantParams) throws NegocioException{
        Merchant merchant = new Merchant();
        merchant.setCode(merchantParams.getCode());
        merchant.setName(merchantParams.getName());
        merchant.setCompanyFee(merchantParams.getCompanyFee());
        merchant.setPersonFee(merchantParams.getPersonFee());
        List<Country> countries = new ArrayList<>();
        for (String countryIso: merchantParams.getCountryCodes()) {
            Optional<Country> country = countryDao.findById(countryIso);
            if (country.isEmpty()){
                throw new NegocioException("El pais "+countryIso+" no se encuentra en el sistema");
            }
            countries.add(country.get());
        }

        merchant.setCountries(countries);
        merchantDao.save(merchant);

    }

    @Override
    public Optional<Merchant> getMerchant(String code) {
        return merchantDao.findById(code);
    }

    @Override
    public List<Merchant> list() {
        return merchantDao.findAll();
    }

    @Override
    public void delete(Merchant merchant) {

    }
}
