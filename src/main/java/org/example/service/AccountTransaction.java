package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Account;
import org.example.model.State;
import org.example.repository.EventRepository;
import org.example.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTransaction {
    private final EventRepository eventRepository;
    private final StateRepository stateRepository;

    public Account saveAccount(Account account,Integer id) {
       return eventRepository.save(account.setState(stateRepository.findById(id).orElseThrow(() ->
               new RuntimeException("State not found" + id))));
        }
        public List<Account> readAll() {
        return eventRepository.findAll();
    }
        public List<Account> readByStateId(Integer id) {
        return eventRepository.findByStateId(id);
        }
    public Account readById(String id) {
        return eventRepository.findByExternalId(id);
    }

}

