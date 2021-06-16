package com.uy.cobranza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Client {

    @Id
    @Column(name="number")
    private Integer number ;

    @Column(name = "name")
    private String name;

    @Column(name = "email" , unique = true)
    private String email;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private ClientType type;

    @Column(name = "country_iso", nullable = false)
    private String countryIso;



    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "client_code")
    private List<Transaction> transactions;


}
