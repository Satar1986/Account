package org.example.handler;

import lombok.RequiredArgsConstructor;
import org.example.event.ProductEvent;

import org.example.service.AccountProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class ProductEventHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
   private final AccountProcessService accountProcessService;

    @KafkaListener(topics = "Account_bank")
    public void handle(@Payload ProductEvent productEvent,
                       @Header("externalId") String externalId
    ) {
        logger.info("Received event: {}", productEvent.getExternalId());

        try {
            accountProcessService.processMessage(productEvent);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}











