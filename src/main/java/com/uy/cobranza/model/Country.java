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
    @OneToMany
    @JoinColumn(name = "coutry_iso")
    private List<Client> clientList;


    @JsonIgnore
    @ManyToMany
    private List<Merchant> merchants;

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

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

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public List<Merchant> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<Merchant> merchants) {
        this.merchants = merchants;
    }
}
