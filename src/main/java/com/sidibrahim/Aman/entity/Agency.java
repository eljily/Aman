package com.sidibrahim.Aman.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "agency")
    private List<User> users;

    @OneToMany(mappedBy = "agency")
    private List<Transaction> transactions;

    private LocalDate createDate;
    private LocalDate updateDate;
}
