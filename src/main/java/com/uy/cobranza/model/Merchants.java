package com.uy.cobranza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Merchants {


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



}
