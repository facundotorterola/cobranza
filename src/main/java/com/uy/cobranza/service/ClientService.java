package com.uy.cobranza.service;

import com.uy.cobranza.dao.ClientDao;
import com.uy.cobranza.exception.BusinessException;
import com.uy.cobranza.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> listClients();

    Optional<Client> getClient(Integer id);

    ClientDao.ClientTypeCountOfTransactionsDto getTheCountOfTransactionsByClient(Integer clientNumber, String sinceDateStr, String untilDateStr);

    void addClient(Client client) throws BusinessException;

    void deleteClient(Client client);
}
