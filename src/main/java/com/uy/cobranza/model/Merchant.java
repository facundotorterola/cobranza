package com.uy.cobranza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Merchant {


    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;


    @Column(name = "person_fee")
    private Double personFee;

    @Column(name = "company_fee")
    private Double companyFee;

    @ManyToMany
    private List<Country> countries;

    @JsonIgnore
    @ManyToMany
    private List<Processor> processors;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "merchant_code")
    private List<Transaction> transactions;


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

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Processor> getProcessors() {
        return processors;
    }

    public void setProcessors(List<Processor> processors) {
        this.processors = processors;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
