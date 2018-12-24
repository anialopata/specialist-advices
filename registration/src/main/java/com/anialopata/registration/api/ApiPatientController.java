package com.anialopata.registration.api;

import com.anialopata.registration.dto.PatientDto;
import com.anialopata.registration.dto.PatientListDto;
import com.anialopata.registration.dto.VisitDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by Ania on 2018-12-04.
 */

@Api(description = "Patient Controller")
public interface ApiPatientController {

    @ResponseStatus(OK)
    PatientListDto getAllPatients();

    @ResponseStatus(OK)
    PatientListDto getAllActivePatients();

    @ResponseStatus(OK)
    @ApiOperation(value = "Get patient by ID number", response = PatientDto.class)
    PatientDto getPatientById(@PathVariable(value = "ID number of the patient") Long id);

    @ResponseStatus(OK)
    PatientDto updatePatient(@PathVariable Long id, @RequestBody PatientDto patientDto);

    @ResponseStatus(OK)
    void deletePatient(@PathVariable Long id);

    @ResponseStatus(OK)
    void addVisitToPatient(@PathVariable Long patientId, @RequestBody VisitDto visitDto);
}
