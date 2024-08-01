package com.sidibrahim.Aman.mapper;

import com.sidibrahim.Aman.dto.AgencyDto;
import com.sidibrahim.Aman.entity.Agency;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

    public AgencyDto toAgencyDto(Agency agency) {
        return AgencyDto.builder()
                .name(agency.getName())
                .agencyCode(agency.getAgencyCode())
                .address(agency.getAddress())
                .build();
    }

    public List<AgencyDto> toAgencyDtos(List<Agency> agencyList) {
        return agencyList.stream().map(this::toAgencyDto).toList();
    }

    public List<Agency> toAgencyList(List<AgencyDto> agencyDtoList) {
        return agencyDtoList.stream().map(this::toAgency).toList();
    }

}
