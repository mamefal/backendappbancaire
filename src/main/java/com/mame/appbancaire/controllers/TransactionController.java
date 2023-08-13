package com.mame.appbancaire.controllers;


import com.mame.appbancaire.dto.AddressDto;
import com.mame.appbancaire.dto.TransactionDto;
import com.mame.appbancaire.services.AddressService;
import com.mame.appbancaire.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody TransactionDto transactionDto
    ) {
        return ResponseEntity.ok(service.save(transactionDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{transaction-id}")
    public ResponseEntity<TransactionDto> findById(
            @PathVariable("transaction-id") Integer transactionId
    ) {
        return ResponseEntity.ok(service.findById(transactionId));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<TransactionDto>> findAllByUserId(
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @DeleteMapping("/{transaction-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("transaction-id") Integer transactionId
    ) {
        service.delete(transactionId);
        return ResponseEntity.accepted().build();
    }




}
