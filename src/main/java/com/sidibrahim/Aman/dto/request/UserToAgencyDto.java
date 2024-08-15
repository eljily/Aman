package com.sidibrahim.Aman.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserToAgencyDto {
    private Long userId;
    private Long agencyId;
}
