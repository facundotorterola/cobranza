package com.uy.cobranza.params;


import lombok.Data;

import java.util.List;

@Data
public class ProcessorParams {


    private String code;

    private String name;

    private Double limit;

    private List<String> merchantCodes;

}
