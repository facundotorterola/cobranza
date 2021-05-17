package com.uy.cobranza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Country {

    @Id
    @Column(name ="iso_code")
    private String  isoCode;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "coutry_iso")
    List<Client> clientList;
}
