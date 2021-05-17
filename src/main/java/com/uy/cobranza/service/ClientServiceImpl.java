package com.uy.cobranza.service;

import com.uy.cobranza.dao.ClientDao;
import com.uy.cobranza.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public List<Client> list() {
        return clientDao.findAll();
    }

    @Override
    public Optional<Client> getClient(Integer id) {
        return clientDao.findById(id);
    }

    @Override
    public void addClient(Client client) {
        clientDao.save(client);
    }

    @Override
    public void deleteClient(Client client) {
        clientDao.delete(client);
    }
}
