package com.sidibrahim.Aman.controller;

import com.sidibrahim.Aman.entity.Agency;
import com.sidibrahim.Aman.exception.GenericException;
import com.sidibrahim.Aman.repository.AgencyRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agencies")
public class AgencyController {
    private final AgencyRepository agencyRepository;

    public AgencyController(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    //Todo : Use Service here and ApiResponse and Return Dtos
    public ResponseEntity<List<Agency>> getAll(){
        return ResponseEntity.ok(agencyRepository.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    //Todo: Move logic to Service and return ApiResponse
    public ResponseEntity<Agency> getById(@PathVariable Long id){
        return ResponseEntity.ok(agencyRepository.findById(id).orElseThrow(()->new GenericException("Agency Not Found")));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    //Todo: Should Return Dto Or ApiResponse , move logic to Service
    public ResponseEntity<Agency> addAgency(@RequestBody Agency agency) {
        return ResponseEntity.ok(agencyRepository.save(agency));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    //Todo : Need to refactor this , move logic to service .
    public ResponseEntity<?> deleteAgency(@PathVariable Long id){
        Optional<Agency> agency = agencyRepository.findById(id);
        if (agency.isPresent()){
            agencyRepository.deleteById(id);
            return ResponseEntity.ok("Agency Deleted Successfully");
        }
        return ResponseEntity.ok("Agency Does Not Exist");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    //Todo: This need to be removed to service.
    public ResponseEntity<Agency> updateAgency(@PathVariable Long id, @RequestBody Agency updatedAgency) {
        Optional<Agency> optionalAgency = agencyRepository.findById(id);
        if (optionalAgency.isEmpty()) {
            throw new GenericException("Agency not found");
        }

        Agency agencyToUpdate = optionalAgency.get();
        agencyToUpdate.setName(updatedAgency.getName()!=null? updatedAgency.getName() : agencyToUpdate.getName());
        agencyToUpdate.setAddress(updatedAgency.getAddress()!=null? updatedAgency.getAddress() : agencyToUpdate.getAddress());

        Agency savedAgency = agencyRepository.save(agencyToUpdate);
        return ResponseEntity.ok(savedAgency);
    }
}
