package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.model.Account;
import org.example.service.AccountProcessService;
import org.example.service.AccountTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@Tag(name = "Account")
@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private  final AccountTransaction accountTransaction;
    @Operation(
summary = "Выводит список account "
    )
    @GetMapping()
        public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<>(accountTransaction.readAll(),HttpStatus.OK);
    }
    @Operation(
            summary = "Выводит список account по состоянию счета"
    )
    @GetMapping("/state/{id}")
    public ResponseEntity<List<Account>> readByStateId(@PathVariable Integer id) {
        return new ResponseEntity<>(accountTransaction.readByStateId(id),HttpStatus.OK);
    }
    @Operation(
            summary = "Находит account по externalId"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable String id) {
     return new ResponseEntity<>(accountTransaction.readById(id),HttpStatus.OK);
    }

    }
