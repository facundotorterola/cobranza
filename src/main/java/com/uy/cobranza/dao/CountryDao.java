package com.uy.cobranza.dao;

import com.uy.cobranza.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryDao  extends JpaRepository<Country, String> {
}
