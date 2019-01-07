package com.anialopata.registration.service.impl;

import com.anialopata.registration.dto.PatientDto;
import com.anialopata.registration.dto.SimpleVisitDto;
import com.anialopata.registration.dto.VisitDto;
import com.anialopata.registration.exception.UserNotFoundException;
import com.anialopata.registration.mapper.PatientMapper;
import com.anialopata.registration.mapper.VisitMapper;
import com.anialopata.registration.model.Patient;
import com.anialopata.registration.model.Visit;
import com.anialopata.registration.repository.PatientRepository;
import com.anialopata.registration.repository.UserRepository;
import com.anialopata.registration.repository.VisitRepository;
import com.anialopata.registration.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Created by Ania on 2018-11-02.
 */
@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final VisitMapper visitMapper;
    private final VisitRepository visitRepository;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper, VisitMapper visitMapper, VisitRepository visitRepository) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.visitMapper = visitMapper;
        this.visitRepository = visitRepository;
    }

    @Override
    public List<PatientDto> getAllActivePatients() {
        return patientRepository
                .findByIsActiveIsTrue()
                .stream()
                .map(patient -> {
                    PatientDto patientDto = patientMapper.patientToPatientDTO(patient);
                    return patientDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientDto> getAllPatients() {
        return patientRepository
                .findAll()
                .stream()
                .map(patient -> {
                    PatientDto patientDto = patientMapper.patientToPatientDTO(patient);
                    return patientDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto getPatientById(Long id) {
        return patientRepository
                .findById(id)
                .map(patientMapper::patientToPatientDTO)
                .orElseThrow(() -> new UserNotFoundException("Patient with id = " + id + "doesn't exist"));
    }

    @Override
    public PatientDto updatePatient(Long id, PatientDto patientDto) {
        Patient patient = patientRepository.getOne(id);
        patient.setEmail(patientDto.getEmail());
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setPesel(patientDto.getLastName());
        patient.setGender(patientDto.getGender());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setUsername(patientDto.getUsername());
        patientRepository.save(patient);
        return patientMapper.patientToPatientDTO(patient);
    }


    @Override
    public void deletePatient(Long id) {
//        Optional<Patient> patient = patientRepository.findById(id);
//        if(!patient.isPresent())throw new UserNotFoundException("Patient doesn't exist");
//        patientRepository.deleteById(id);

        PatientDto patientDto = getPatientById(id);
        patientDto.setActive(false);
        Patient patient = patientMapper.patientDTOToPatient(patientDto);
        Patient saved = patientRepository.save(patient);
    }

    @Override
    public long addVisitToPatient(Long patientId, VisitDto visitDto) {
        Visit visit = visitMapper.visitDtoToVisit(visitDto);
        Optional<Patient> patient = patientRepository.findById(patientId);
        if(patient.isPresent()) {
            Patient p = patient.get();
            visit.setPatient(p);
            visitRepository.save(visit);
            patient.get().addVisit(visit);
            patientRepository.save(p);
            return visit.getId();
        } else {
            throw new UserNotFoundException(format("No patient with id: %d", patientId));
        }
    }

}
