package com.sidibrahim.Aman.mapper;

import com.sidibrahim.Aman.dto.TransactionDto;
import com.sidibrahim.Aman.dto.UserDto;
import com.sidibrahim.Aman.entity.Transaction;
import com.sidibrahim.Aman.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class TransactionMapper {
    public Transaction toTransaction(TransactionDto transactionDto){
        return Transaction.builder()
                .id(transactionDto.getId())
                .amount(transactionDto.getAmount())
                .note(transactionDto.getNote())
                .customerOtp(transactionDto.getCustomerOtp())
                .type(transactionDto.getType())
                .build();
    }

    public TransactionDto toTransactionDto(Transaction transaction){
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .note(transaction.getNote())
                .customerOtp(transaction.getCustomerOtp())
                .type(transaction.getType())
                .createDate(transaction.getCreateDate())
                .updateDate(transaction.getUpdateDate())
                .customerPhoneNumber(transaction.getCustomerPhoneNumber())
                .customerName(transaction.getCustomerName())
                .build();
    }

    public List<TransactionDto> toTransactionDtos(List<Transaction> transactions) {
        return transactions.stream().map(this::toTransactionDto).collect(Collectors.toList());
    }

    public List<Transaction> toTransactions(List<TransactionDto> transactionsDtos){
        return transactionsDtos.stream().map(this::toTransaction).collect(Collectors.toList());
    }
}
