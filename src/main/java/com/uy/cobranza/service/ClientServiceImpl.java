package com.uy.cobranza.service;

import com.uy.cobranza.dao.ClientDao;
import com.uy.cobranza.exception.BusinessException;
import com.uy.cobranza.model.Client;
import com.uy.cobranza.model.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private CountrySerivce countrySerivce;

    @Override
    public List<Client> listClients() {
        return clientDao.findAll();
    }

    @Override
    public Optional<Client> getClient(Integer id) {
        return clientDao.findById(id);
    }

    @Override
    public ClientDao.ClientTypeCountOfTransactionsDto getTheCountOfTransactionsByClient(Integer clientNumber, String sinceDateStr, String untilDateStr) {
        return clientDao.getTheCountOfTransactionsByClient(clientNumber,sinceDateStr,untilDateStr);
    }


    @Override
    public void addClient(Client client) throws BusinessException {
        try {
            if (client.getCountryIso()!=null){
                Optional<Country> countryOp =  countrySerivce.getCountry(client.getCountryIso());
                if (countryOp.isEmpty()){
                    throw new BusinessException("El Pais "+client.getCountryIso()+" no se encuentra en el sistema ");
                }
            }
            clientDao.save(client);
        } catch (DataIntegrityViolationException e ){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void deleteClient(Client client) {
        clientDao.delete(client);
    }
}
