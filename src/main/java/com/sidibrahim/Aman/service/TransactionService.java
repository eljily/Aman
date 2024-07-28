package com.sidibrahim.Aman.service;

import com.sidibrahim.Aman.entity.Transaction;
import com.sidibrahim.Aman.entity.User;
import com.sidibrahim.Aman.exception.GenericException;
import com.sidibrahim.Aman.repository.TransactionRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class TransactionService {
    /*
    Should Always in A Service to return dtos instead of model
     */
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction save(Transaction transaction,@AuthenticationPrincipal User user) {
        transaction.setAgency(user.getAgency());
        transaction.setCreateDate(LocalDateTime.now());
        transaction.setAgent(user);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(()->new GenericException("User Not Found"));
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> findByAgent(Long id) {
        return transactionRepository.findByAgentId(id);
    }
}
