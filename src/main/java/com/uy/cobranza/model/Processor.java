package com.uy.cobranza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Processor {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "processor_limit")
    private Double limit;

    @ManyToMany
    private List<Merchants> merchants;


    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "transaction_code")
    private List<Transaction> transactions;


}
