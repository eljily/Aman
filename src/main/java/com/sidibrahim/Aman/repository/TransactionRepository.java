package com.sidibrahim.Aman.repository;

import com.sidibrahim.Aman.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAgentId(Long id);

}
