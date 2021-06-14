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



}
