package com.sidibrahim.Aman.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name cant be null")
    private String name;

    @NotNull(message = "address cant be null")
    private String address;

    @NotNull(message = "agencyCode cant be null")
    private String agencyCode;

    @OneToMany(mappedBy = "agency")
    private List<User> users;

    @OneToMany(mappedBy = "agency")
    private List<Transaction> transactions;

    @CreationTimestamp
    private LocalDate createDate;

    @UpdateTimestamp
    private LocalDate updateDate;

    private Boolean isDeleted;
}
