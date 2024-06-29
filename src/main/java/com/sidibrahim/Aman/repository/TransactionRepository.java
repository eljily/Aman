package com.sidibrahim.Aman.repository;

import com.sidibrahim.Aman.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
