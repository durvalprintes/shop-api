package com.casa.codigo.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.casa.codigo.dto.ShopStatusDto;
import com.casa.codigo.repository.ShopRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidatorListener {

  private final ShopRepository repository;

  @KafkaListener(topics = "${topic.shop.status}", groupId = "group")
  public void listenShopStatus(ShopStatusDto dto) {
    try {
      log.info("Status da compra recebida no tópico: {}.", dto.getIdentifier());
      repository.findByIdentifier(dto.getIdentifier()).ifPresentOrElse(shop -> {
        shop.setStatus(dto.getStatus());
        repository.save(shop);
      }, () -> new Exception("Compra não foi identificada."));
    } catch (Exception e) {
      log.error("Erro no processamento da compra {}", dto.getIdentifier());
    }

  }

}