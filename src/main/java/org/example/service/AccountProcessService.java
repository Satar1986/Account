package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.event.ProductEvent;
import org.example.event.EventRequisites;
import org.example.model.Account;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountProcessService {
    private final AccountTransaction accountTransaction;
    private final KafkaRequisitesProducer kafkaRequisitesProducer;

    public void processMessage(ProductEvent productEvent) {
        Account account = accountTransaction.readById(productEvent.getExternalId());
        if (account != null) {
            account.setClientId(productEvent.getClientId());
            account.setNameAccount(productEvent.getNameAccount());
            account.setMinRemainder(productEvent.getMinRemainder());
            account.setInterestIsPaid(productEvent.getInterestIsPaid());
            account.setInterestRate(productEvent.getInterestRate());
            account.setExternalId(productEvent.getExternalId());
            account.setClientId(productEvent.getClientId());
            account.setSum(productEvent.getSum());
            account.setCurrency(productEvent.getCurrency());
            Account savedAccount = accountTransaction.saveAccount(account, productEvent.getStateId());

            kafkaRequisitesProducer.processMessage(EventRequisites.builder().
                    clientId(productEvent.getClientId()).
                    externalId(productEvent.getExternalId()).
                    kpp(productEvent.getKpp()).
                    ogrn(productEvent.getOgrn()).
                    rcbic(productEvent.getRcbic()).
                    address(productEvent.getAddress()).
                    businessAddress(productEvent.getBusinessAddress()).
                    corrAss(productEvent.getCorrAss()).
                    ass(productEvent.getAss()).
                    bankName(productEvent.getBankName()).
                    inn(productEvent.getInn()).
                    nameCompany(productEvent.getNameCompany()).
                    requisitesId(productEvent.getRequisitesId()).
                    build());

        } else {
            Account account1 = Account.builder().
                    nameAccount(productEvent.getNameAccount()).
                    clientId(productEvent.getClientId()).
                    externalId(productEvent.getExternalId()).
                    sum(productEvent.getSum()).
                    currency(productEvent.getCurrency()).
                    interestRate(productEvent.getInterestRate()).
                    interestIsPaid(productEvent.getInterestIsPaid()).
                    minRemainder(productEvent.getMinRemainder()).build();
            Account savedAccount = accountTransaction.saveAccount(account1, productEvent.getStateId());

            kafkaRequisitesProducer.processMessage(EventRequisites.builder().
                    clientId(productEvent.getClientId()).
                    externalId(productEvent.getExternalId()).
                    kpp(productEvent.getKpp()).
                    ogrn(productEvent.getOgrn()).
                    rcbic(productEvent.getRcbic()).
                    address(productEvent.getAddress()).
                    businessAddress(productEvent.getBusinessAddress()).
                    corrAss(productEvent.getCorrAss()).
                    ass(productEvent.getAss()).
                    bankName(productEvent.getBankName()).
                    inn(productEvent.getInn()).
                    nameCompany(productEvent.getNameCompany()).
                    requisitesId(productEvent.getRequisitesId()).
                    build());
        }
    }
}

