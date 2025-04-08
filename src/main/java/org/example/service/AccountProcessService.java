package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.event.AccountEvent;
import org.example.event.ProductEvent;
import org.example.event.EventRequisites;
import org.example.model.Account;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@EnableAsync
@RequiredArgsConstructor
public class AccountProcessService {
    private final AccountTransaction accountTransaction;
    private final KafkaRequisitesProducer kafkaRequisitesProducer;
    private final KafkaAccountProducer kafkaAccountProducer;

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
@Async
@Scheduled(cron = "0 0 16 8 * ?", zone = "Europe/Moscow")
public void scheduledProcess() {
    List<Account> list = accountTransaction.readAll();
    list.stream().map(this::getEvent).forEach(sentEvent -> kafkaAccountProducer.send(sentEvent));
}
public AccountEvent getEvent(Account account) {
        AccountEvent accountEvent = new AccountEvent();
        accountEvent.setId(account.getId());
        accountEvent.setExternalId(account.getExternalId());
        accountEvent.setClientId(account.getClientId());
        accountEvent.setNameAccount(account.getNameAccount());
        accountEvent.setMinRemainder(account.getMinRemainder());
        accountEvent.setInterestIsPaid(account.getInterestIsPaid());
        accountEvent.setInterestRate(account.getInterestRate());
        accountEvent.setInterestIsPaid(account.getInterestIsPaid());
        accountEvent.setMinRemainder(account.getMinRemainder());
        accountEvent.setSum(account.getSum());
        accountEvent.setCreated(account.getCreated());
        accountEvent.setUpdated(account.getUpdated());
        return accountEvent;
}
}

