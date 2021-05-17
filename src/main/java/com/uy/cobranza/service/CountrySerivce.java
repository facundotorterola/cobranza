package com.uy.cobranza.service;

import com.uy.cobranza.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountrySerivce {
    void addCountry(Country country);

    void deleteCountry(Country country);

    Optional<Country> getCountry(String isoCode);

    List<Country> listCountries();

}
