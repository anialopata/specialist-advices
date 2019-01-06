package com.anialopata.registration.api.impl;

import com.anialopata.registration.api.ApiSpecialistController;
import com.anialopata.registration.dto.PatientDto;
import com.anialopata.registration.dto.SpecialistDto;
import com.anialopata.registration.dto.SpecialistListDto;
import com.anialopata.registration.dto.VisitDto;
import com.anialopata.registration.service.SpecialistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Ania on 2018-11-11.
 */
@Api(description = "Specialist Controller")
@RestController
@RequestMapping("/api/v1/specialists")
@CrossOrigin(origins = "http://localhost:4200")
public class SpecialistController implements ApiSpecialistController {

    private final SpecialistService specialistService;

    public SpecialistController(SpecialistService specialistService) {
        this.specialistService = specialistService;
    }

    @GetMapping
    @RequestMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    public SpecialistListDto getActiveSpecialists() { return new SpecialistListDto(specialistService.getAllActiveSpecialists());}

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SpecialistListDto getAllSpecialists(Authentication authentication) {

        return new SpecialistListDto(specialistService.getAllSpecialists());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public SpecialistDto getSpecialistById(@PathVariable Long id){
        return specialistService.getSpecialistById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SpecialistDto createNewSpecialist(@RequestBody SpecialistDto specialistDto){
        return specialistService.createSpecialist(specialistDto);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public SpecialistDto updateSpecialist(@PathVariable Long id, @RequestBody SpecialistDto specialistDto){
        return specialistService.saveSpecialistByDto(id, specialistDto);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteSpecialist(@PathVariable Long id){
        specialistService.deleteSpecialist(id);
    }


    @ApiOperation(value = "List all visits for Specialist")
    @GetMapping("/{specialistId}/visits")
    public Set<VisitDto> findSpecialistVisits(@PathVariable Long specialistId) {
        return specialistService.findSpecialistVisits(specialistId);
    }

    @ApiOperation(value = "Add visit to Specialist")
    @PostMapping("/{specialistId}/visits/patient/{patientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public long addVisitToSpecialist(@PathVariable Long specialistId, @RequestBody VisitDto visitDto, @PathVariable Long patientId){
        return specialistService.addVisitToSpecialist(specialistId, visitDto, patientId);

    }




}
