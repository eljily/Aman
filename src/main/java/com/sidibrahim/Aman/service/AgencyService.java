package com.sidibrahim.Aman.service;

import com.sidibrahim.Aman.dto.AgencyDto;
import com.sidibrahim.Aman.entity.Agency;
import com.sidibrahim.Aman.exception.GenericException;
import com.sidibrahim.Aman.mapper.AgencyMapper;
import com.sidibrahim.Aman.repository.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyService {

    private final AgencyRepository agencyRepository;
    private final AgencyMapper agencyMapper;

    public AgencyDto save(Agency agency){
        return agencyMapper.toAgencyDto(agencyRepository.save(agency));
    }

    public List<AgencyDto> getAll(){
        return agencyMapper.toAgencyDtos(agencyRepository.findAll());
    }

    public void deleteById(Long id){
        agencyRepository.deleteById(id);
    }

    public AgencyDto getById(Long id){
        return agencyMapper.toAgencyDto(agencyRepository.findById(id).orElseThrow(()->new GenericException("Agency Not Found With Id : " + id)));
    }
}
