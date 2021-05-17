package com.uy.cobranza.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer number ;

    @Column(name = "name")
    private String name;

    @Column(name = "email" , unique = true)
    private String email;

    @Column(name="type")
    private String type;

   @Column(name = "country_iso")
    private String countryIso;



}
