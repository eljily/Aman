package com.sidibrahim.Aman.entity;

import com.sidibrahim.Aman.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String note;
    private String customerOtp;
    private String customerName;
    private String customerPhoneNumber;
    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private User agent;

    private Boolean isDeleted;

}
