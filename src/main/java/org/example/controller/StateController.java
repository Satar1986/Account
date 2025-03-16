package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.State;
import org.example.service.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
@AllArgsConstructor
public class StateController {
    private StateService restService;
     @GetMapping
    public ResponseEntity<List<State>> readAll(){
         return new ResponseEntity<>(restService.readAll(), HttpStatus.OK);
     }
     @GetMapping("/states/{id}")
    public ResponseEntity<State> getState(@PathVariable Integer id){
         return new ResponseEntity<>(restService.readByStateId(id),HttpStatus.OK);
     }
}
