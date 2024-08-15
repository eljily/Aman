package com.sidibrahim.Aman.dto;

import com.sidibrahim.Aman.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionDto {
    private Long id;
    private String note;
    private String customerOtp;
    private String customerName;
    private String customerPhoneNumber;
    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long agencyId;
}
