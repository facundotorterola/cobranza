package com.uy.cobranza.dao;

import com.uy.cobranza.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantDao extends JpaRepository<Merchant, String> {
}
