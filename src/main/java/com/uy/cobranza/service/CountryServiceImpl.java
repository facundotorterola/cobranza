package com.uy.cobranza.service;

import com.uy.cobranza.dao.CountryDao;
import com.uy.cobranza.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountrySerivce {

    @Autowired
    private CountryDao countryDao;

    @Override
    @CacheEvict(value = "countries",allEntries = true)
    public void addCountry(Country country) {
        countryDao.save(country);
    }

    @Override
    @CacheEvict(value = "countries",allEntries = true)
    public void deleteCountry(Country country) {
        countryDao.delete(country);
    }

    @Override
    @Cacheable("countries")
    public Optional<Country> getCountry(String isoCode) {
        return countryDao.findById(isoCode);
    }

    @Override
    @Cacheable("countries")
    public List<Country> listCountries() {
        return countryDao.findAll();
    }



}
