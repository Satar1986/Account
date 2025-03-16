package org.example.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.event.EventRequisites;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaRequisitesProducer {
    private final KafkaTemplate<String, EventRequisites> kafkaTemplate;
    public void processMessage(EventRequisites eventRequisites) {
        ProducerRecord<String,EventRequisites>record=new ProducerRecord<>(
                "Account_requisites",
                eventRequisites.getExternalId(),
                eventRequisites
        );
        record.headers().add("externalId",eventRequisites.getExternalId().getBytes());
        kafkaTemplate.send(record);
    }

}
