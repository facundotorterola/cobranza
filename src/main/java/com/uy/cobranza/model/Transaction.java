package com.uy.cobranza.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Transaction {
    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;


    @Column(name = "amount")
    private Double amount;

    @Column(name = "client_code")
    private Integer clientCode;

    @Column(name = "merchant_code")
    private String merchantCode;

    @Column(name = "processor_code")
    private String processorCode;



}
