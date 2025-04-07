package org.example.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.event.EventRequisites;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaRequisitesProducer {
    private final KafkaTemplate<String, EventRequisites> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public void processMessage(EventRequisites eventRequisites) {
        ProducerRecord<String,EventRequisites>record=new ProducerRecord<>(
                "Account_requisites",
                eventRequisites.getExternalId(),
                eventRequisites
        );
        record.headers().add("externalId",eventRequisites.getExternalId().getBytes());
        CompletableFuture<SendResult<String,EventRequisites>>future=
        kafkaTemplate.send(record);
        future.whenComplete((result,exeption)->{
            if(exeption!=null){
                logger.error("Failed to send message: {}", exeption.getMessage());
            }else{
                logger.info("Successfully sent message: {}", result.getRecordMetadata());
            }
        });
        logger.info("Return: {}", eventRequisites.getExternalId());
    }

}
