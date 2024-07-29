package com.sidibrahim.Aman.dto;
import lombok.*;


import java.time.LocalDate;


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
