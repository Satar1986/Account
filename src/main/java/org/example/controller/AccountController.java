package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.Account;
import org.example.service.AccountTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private  final AccountTransaction accountTransaction;
    @GetMapping()
        public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<>(accountTransaction.readAll(),HttpStatus.OK);
    }
    @GetMapping("/state/{id}")
    public ResponseEntity<List<Account>> readByStateId(@PathVariable Integer id) {
        return new ResponseEntity<>(accountTransaction.readByStateId(id),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable String id) {
     return new ResponseEntity<>(accountTransaction.readById(id),HttpStatus.OK);
    }
    }
