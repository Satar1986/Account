package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.State;
import org.example.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StateService {
    private StateRepository stateRepository;
    public List<State> readAll() {
        return stateRepository.findAll();
    }
    public State readByStateId(Integer id) {
        return stateRepository.findById(id).orElseThrow(() ->
                new RuntimeException("State not found" + id));
    }
}
