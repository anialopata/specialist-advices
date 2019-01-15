package com.anialopata.registration.security.controller;

/**
 * Created by Ania on 2018-11-15.
 */


import com.anialopata.registration.dto.PatientDto;
import com.anialopata.registration.dto.SpecialistDto;
import com.anialopata.registration.mapper.PatientMapper;
import com.anialopata.registration.model.User;
import com.anialopata.registration.security.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @Autowired
    private SignupService signupService;

    @Autowired
    private PatientMapper patientMapper;

    @RequestMapping(value = "/signup/patient", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody PatientDto user) {
        User patient = patientMapper.patientDTOToPatient(user);
        signupService.addPatient(patient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/signup/specialist", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody SpecialistDto user) {
        signupService.addSpecialist(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

