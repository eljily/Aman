package com.sidibrahim.Aman.repository;

import com.sidibrahim.Aman.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAgentId(Long id);
    List<Transaction> findByAgency_Id(Long id);

}
