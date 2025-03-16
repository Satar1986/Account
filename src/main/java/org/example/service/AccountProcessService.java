package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.event.ProductEvent;
import org.example.event.EventRequisites;
import org.example.model.Account;

import org.example.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountProcessService {
    private final AccountTransaction accountTransaction;
   private final KafkaRequisitesProducer kafkaRequisitesProducer;
private final Logger logger = LoggerFactory.getLogger(AccountProcessService.class);


    public void processMessage(ProductEvent productEvent) {
        Account account = Account.builder().
                name_account(productEvent.getName_account()).
                client_id(productEvent.getClient_id()).
                externalId(productEvent.getExternalId()).
                sum(productEvent.getSum()).
                currency(productEvent.getCurrency()).
                interest_rate(productEvent.getInterest_rate()).
                interest_is_paid(productEvent.getInterest_is_paid()).
                min_remainder(productEvent.getMin_remainder()).build();
        Account savedAccount = accountTransaction.saveAccount(account, productEvent.getState_id());


        kafkaRequisitesProducer.processMessage(EventRequisites.builder().
                client_id(productEvent.getClient_id()).
                externalId(productEvent.getExternalId()).
                kpp(productEvent.getKpp()).
                ogrn(productEvent.getOgrn()).
                rcbic(productEvent.getRcbic()).
                address(productEvent.getAddress()).
                business_address(productEvent.getBusiness_address()).
                corr_ass(productEvent.getCorr_ass()).
                ass(productEvent.getAss()).
                bank_name(productEvent.getBank_name()).
                inn(productEvent.getInn()).
                name_company(productEvent.getName_company()).
                build());

    }
}
