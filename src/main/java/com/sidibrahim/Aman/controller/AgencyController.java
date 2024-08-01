package com.sidibrahim.Aman.controller;

import com.sidibrahim.Aman.dto.AgencyDto;
import com.sidibrahim.Aman.entity.Agency;
import com.sidibrahim.Aman.exception.GenericException;
import com.sidibrahim.Aman.mapper.AgencyMapper;
import com.sidibrahim.Aman.repository.AgencyRepository;
import com.sidibrahim.Aman.service.AgencyService;
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
    private final AgencyService agencyService;
    private final AgencyMapper agencyMapper;

    public AgencyController(AgencyRepository agencyRepository, AgencyService agencyService, AgencyMapper agencyMapper) {
        this.agencyRepository = agencyRepository;
        this.agencyService = agencyService;
        this.agencyMapper = agencyMapper;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    public ResponseEntity<List<AgencyDto>> getAll() {
        return ResponseEntity.ok(agencyService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    public ResponseEntity<AgencyDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(agencyService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<AgencyDto> addAgency(@RequestBody Agency agency) {
        return ResponseEntity.ok(agencyService.save(agency));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    public ResponseEntity<?> deleteAgency(@PathVariable Long id) {
        Optional<Agency> agency = agencyRepository.findById(id);
        if (agency.isPresent()) {
            agencyService.deleteById(id);
            return ResponseEntity.ok("Agency Deleted Successfully");
        }
        return ResponseEntity.ok("Agency Does Not Exist");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<AgencyDto> updateAgency(@PathVariable Long id, @RequestBody Agency updatedAgency) {
        Optional<Agency> optionalAgency = agencyRepository.findById(id);
        if (optionalAgency.isEmpty()) {
            throw new GenericException("Agency not found");
        }

        Agency agencyToUpdate = optionalAgency.get();
        agencyToUpdate.setName(updatedAgency.getName() != null ? updatedAgency.getName() : agencyToUpdate.getName());
        agencyToUpdate.setAddress(updatedAgency.getAddress() != null ? updatedAgency.getAddress() : agencyToUpdate.getAddress());

        Agency savedAgency = agencyRepository.save(agencyToUpdate);
        return ResponseEntity.ok(agencyMapper.toAgencyDto(savedAgency));
    }
}
