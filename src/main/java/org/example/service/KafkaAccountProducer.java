package org.example.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.event.AccountEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaAccountProducer {
    private final KafkaTemplate<String, AccountEvent> kafkaTemplate;
    public void send(AccountEvent accountEvent) {
        ProducerRecord<String, AccountEvent> record = new ProducerRecord<>(
                "Account_archive",
                accountEvent.getExternalId(),
                accountEvent
        );
        record.headers().add("externalId",accountEvent.getExternalId().getBytes());
        kafkaTemplate.send(record);
    }
}
