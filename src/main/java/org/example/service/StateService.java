package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.State;
import org.example.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateService {
    private final StateRepository stateRepository;
    public List<State> readAll() {
        return stateRepository.findAll();
    }
    public State readByStateId(Integer id) {
        return stateRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Exeption" + id));
    }
}
