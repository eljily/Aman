package com.sidibrahim.Aman.service;

import com.sidibrahim.Aman.dto.TransactionDto;
import com.sidibrahim.Aman.entity.Transaction;
import com.sidibrahim.Aman.entity.User;
import com.sidibrahim.Aman.exception.GenericException;
import com.sidibrahim.Aman.mapper.TransactionMapper;
import com.sidibrahim.Aman.repository.TransactionRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    public TransactionDto save(Transaction transaction, @AuthenticationPrincipal User user) {
        transaction.setAgency(user.getAgency());
        transaction.setCreateDate(LocalDateTime.now());
        transaction.setAgent(user);
        return transactionMapper
                .toTransactionDto(transactionRepository
                        .save(transaction));
    }

    public List<TransactionDto> findAll() {
        return
                transactionMapper.toTransactionDtos(transactionRepository.findAll());
    }

    public TransactionDto findById(Long id) {
        return transactionMapper
                .toTransactionDto(transactionRepository
                        .findById(id)
                        .orElseThrow(() -> new GenericException("Transaction Not Found")));
    }

    public void delete(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new GenericException("Transaction Not Found"));
        transactionRepository.deleteById(transaction.getId());
    }

    public List<TransactionDto> findByAgentId(Long id) {
        return transactionMapper.toTransactionDtos(transactionRepository.findByAgentId(id));
    }

    public List<TransactionDto> findByAgencyId(Long id){
        return transactionMapper.toTransactionDtos(transactionRepository.findByAgency_Id(id));
    }
}
