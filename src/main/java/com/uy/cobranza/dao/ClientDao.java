package com.uy.cobranza.dao;

import com.uy.cobranza.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ClientDao  extends JpaRepository<Client, Integer> {
}
