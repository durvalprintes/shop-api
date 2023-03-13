package com.casa.codigo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.casa.codigo.dto.ShopDto;

@Service
public class KafkaClient {
  
  @Autowired
  private KafkaTemplate<String, ShopDto> kafkaTemplate;

  @Value(value = "${topic}")
  private String topic;

  public void sendMessage(ShopDto dto) {
    kafkaTemplate.send(topic, dto);
  }
  
}
