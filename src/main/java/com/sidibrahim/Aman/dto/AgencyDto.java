package com.sidibrahim.Aman.dto;

import com.sidibrahim.Aman.entity.Transaction;
import com.sidibrahim.Aman.entity.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AgencyDto {

    private Long id;

    private String name;

    private String address;

    private String agencyCode;

    private LocalDate createDate;

    private LocalDate updateDate;
}
