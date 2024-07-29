package com.sidibrahim.Aman.service;

import com.sidibrahim.Aman.entity.Agency;
import com.sidibrahim.Aman.exception.GenericException;
import com.sidibrahim.Aman.repository.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyService {

    private final AgencyRepository agencyRepository;

    public Agency save(Agency agency){
        return agencyRepository.save(agency);
    }

    public List<Agency> getAll(){
        return agencyRepository.findAll();
    }

    public void deleteById(Long id){
        agencyRepository.deleteById(id);
    }

    public Agency getById(Long id){
        return agencyRepository.findById(id).orElseThrow(()->new GenericException("Agency Not Found With Id : " + id));
    }
}
