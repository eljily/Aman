package com.sidibrahim.Aman.controller;

import com.sidibrahim.Aman.entity.Transaction;
import com.sidibrahim.Aman.entity.User;
import com.sidibrahim.Aman.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    //TODO : NEVER USE REPOSITORY IN CONTROLLER AND ALWAYS RETURN DTOS
    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @PostMapping
    @Transactional
    //TODO: MOVE LOGIC TO SERVICE  USE DTOS.
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction, @AuthenticationPrincipal User user) {
        System.out.println("Connected user :"+user.getName());
        transaction.setAgency(user.getAgency());
        transaction.setCreateDate(LocalDateTime.now());
        transaction.setAgent(user);
        return ResponseEntity.ok(transactionRepository.save(transaction));
    }

    @GetMapping
    public ResponseEntity<?> getAllTransactions() {
        return ResponseEntity.ok(transactionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionRepository.findById(id));
    }

    @GetMapping("/{id}/agent")
    public ResponseEntity<?> getTransactionByAgent(@PathVariable Long id) {
        return ResponseEntity.ok(transactionRepository.findByAgentId(id));
    }
}
