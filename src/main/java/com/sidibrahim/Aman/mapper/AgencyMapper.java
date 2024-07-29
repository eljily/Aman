package com.sidibrahim.Aman.mapper;

import com.sidibrahim.Aman.dto.AgencyDto;
import com.sidibrahim.Aman.entity.Agency;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AgencyMapper {

    public Agency toAgency(AgencyDto agencyDto) {
        return Agency.builder()
                .name(agencyDto.getName())
                .agencyCode(agencyDto.getAgencyCode())
                .address(agencyDto.getAddress())
                .build();
    }

}
