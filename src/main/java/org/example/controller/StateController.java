package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.model.State;
import org.example.service.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(name = "State")
@RestController
@RequestMapping("/state")
@RequiredArgsConstructor
public class StateController {
    private final StateService stateService;
    @Operation(
            summary = "Выводит список состояния account"
    )
     @GetMapping
    public ResponseEntity<List<State>> readAll(){
         return new ResponseEntity<>(stateService.readAll(), HttpStatus.OK);
     }
     @Operation(
             summary = "Выводит состояние account по id"
     )
     @GetMapping("/{id}")
    public ResponseEntity<State> getState(@PathVariable Integer id){
         return new ResponseEntity<>(stateService.readByStateId(id),HttpStatus.OK);
     }
}
