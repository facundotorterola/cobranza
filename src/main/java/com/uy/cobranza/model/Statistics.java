package com.uy.cobranza.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Statistics {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id ;

    @Column(name ="method_name")
    private String methodName;


    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name="run_time")
    private Integer runTime;
}
