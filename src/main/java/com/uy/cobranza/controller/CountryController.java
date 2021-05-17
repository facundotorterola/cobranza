package com.uy.cobranza.controller;

import com.uy.cobranza.model.Country;
import com.uy.cobranza.service.CountrySerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountrySerivce countrySerivce;

    @RequestMapping(method = RequestMethod.GET)
    public List<Country> list() {
        List<Country> countries = countrySerivce.listCountries();
        return countries;
    }


    @RequestMapping(value = "/{isoCode}",method = RequestMethod.GET)
    public Optional<Country> getCountry(@PathVariable("isoCode") String isoCode) {
       return countrySerivce.getCountry(isoCode);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Country country) {
        countrySerivce.addCountry(country);
    }




}
