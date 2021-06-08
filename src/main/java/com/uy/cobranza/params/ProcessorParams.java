package com.uy.cobranza.params;


import lombok.Data;

import java.util.List;

public class ProcessorParams {


    private String code;

    private String name;

    private Double limit;

    private List<String> merchantCodes;


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

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public List<String> getMerchantCodes() {
        return merchantCodes;
    }

    public void setMerchantCodes(List<String> merchantCodes) {
        this.merchantCodes = merchantCodes;
    }
}
