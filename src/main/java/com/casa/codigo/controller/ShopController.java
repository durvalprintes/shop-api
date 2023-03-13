package com.casa.codigo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casa.codigo.constants.Status;
import com.casa.codigo.dto.ShopDto;
import com.casa.codigo.model.Shop;
import com.casa.codigo.repository.ShopRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

  private final ShopRepository repository;

  private final ModelMapper mapper;

  @GetMapping
  public List<ShopDto> getShop() {
    return repository.findAll().stream().map(shop -> mapper.map(shop, ShopDto.class)).toList();
  }

  @PostMapping
  public ShopDto saveShop(@RequestBody ShopDto dto) {
    dto.setIdentifier(UUID.randomUUID().toString());
    dto.setDateShop(LocalDate.now());
    dto.setStatus(Status.PENDING);

    Shop shop = mapper.map(dto, Shop.class);
    shop.getItems().stream().forEach(item -> item.setShop(shop));

    return mapper.map(repository.save(shop), ShopDto.class);
  }
}
