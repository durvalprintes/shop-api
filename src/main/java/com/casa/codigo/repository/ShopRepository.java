package com.casa.codigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casa.codigo.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
  
}
