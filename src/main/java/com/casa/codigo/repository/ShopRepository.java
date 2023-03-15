package com.casa.codigo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casa.codigo.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
  
  public Optional<Shop> findByIdentifier( String identifier);
}
