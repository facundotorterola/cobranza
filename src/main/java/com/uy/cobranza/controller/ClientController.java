package com.uy.cobranza.controller;

import com.uy.cobranza.model.Client;
import com.uy.cobranza.model.Country;
import com.uy.cobranza.service.ClientService;
import com.uy.cobranza.service.CountrySerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Client> list() {
        List<Client> clients = clientService.list();
        return clients;
    }


    @RequestMapping(value = "/{code}",method = RequestMethod.GET)
    public Optional<Client> getClient(@PathVariable("code") Integer code) {
       return clientService.getClient(code);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Client client) {
        clientService.addClient(client);
    }

}
