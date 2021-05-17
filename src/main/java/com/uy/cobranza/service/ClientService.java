package com.uy.cobranza.service;

import com.uy.cobranza.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> list();

    Optional<Client> getClient(Integer id);

    void addClient(Client client);

    void deleteClient(Client client);
}
