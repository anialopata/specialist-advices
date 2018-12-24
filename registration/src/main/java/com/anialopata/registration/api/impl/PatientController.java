package com.anialopata.registration.api.impl;

import com.anialopata.registration.api.ApiPatientController;
import com.anialopata.registration.dto.PatientDto;
import com.anialopata.registration.dto.PatientListDto;
import com.anialopata.registration.dto.VisitDto;
import com.anialopata.registration.service.PatientService;
import com.anialopata.registration.service.VisitService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * Created by Ania on 2018-11-02.
 */
@RestController
@RequestMapping("/api/v1/patients")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController implements ApiPatientController {

    private final PatientService patientService;
    private final VisitService visitService;

    public PatientController(PatientService patientService, VisitService visitService) {
        this.patientService = patientService;
        this.visitService = visitService;
    }

    @ApiOperation(value = "Get all patients", response = PatientListDto.class, responseContainer = "List")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PatientListDto getAllPatients() { return new PatientListDto(patientService.getAllPatients());}

    @ApiOperation(value = "Get all activr patients", response = PatientListDto.class, responseContainer = "List")
    @GetMapping
    @RequestMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    public PatientListDto getAllActivePatients() { return new PatientListDto(patientService.getAllActivePatients());}

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public PatientDto getPatientById(@PathVariable @RequestParam(value = "patientId") Long id){
        return patientService.getPatientById(id);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public PatientDto updatePatient(@PathVariable Long id, @RequestBody PatientDto patientDto){
        return patientService.savePatientByDto(id, patientDto);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
    }

    @ApiOperation(value = "Add visit to Patient")
    @PostMapping("/{patientId}/visits")
    @ResponseStatus(HttpStatus.CREATED)
    public void addVisitToPatient(@PathVariable Long patientId, @RequestBody VisitDto visitDto){
            patientService.addVisitToPatient(patientId, visitDto);
    }

    @ApiOperation(value = "List all visits for Patient")
    @GetMapping("/{patientId}/visits")
    public Set<VisitDto> findPatientsVisits(@PathVariable Long patientId) {
        return visitService.findByPatient(patientId);
    }

}
