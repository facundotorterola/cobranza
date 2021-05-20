package com.uy.cobranza.params;

import lombok.Data;

import java.util.List;


@Data
public class MerchantParams {

    private String code;

    private String name;

    private Double personFee;

    private Double companyFee;

    private List<String> countryCodes;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPersonFee() {
        return personFee;
    }

    public void setPersonFee(Double personFee) {
        this.personFee = personFee;
    }

    public Double getCompanyFee() {
        return companyFee;
    }

    public void setCompanyFee(Double companyFee) {
        this.companyFee = companyFee;
    }
    public List<String> getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(List<String> countryCodes) {
        this.countryCodes = countryCodes;
    }

}
