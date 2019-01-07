package com.anialopata.registration.service;

import com.anialopata.registration.dto.PatientDto;
import com.anialopata.registration.dto.PatientListDto;
import com.anialopata.registration.dto.SimpleVisitDto;
import com.anialopata.registration.dto.VisitDto;
import com.anialopata.registration.model.Patient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by Ania on 2018-11-02.
 */
public interface PatientService {

    /**
     *
     * @return list of patients
     */
    List<PatientDto> getAllPatients();

    List<PatientDto> getAllActivePatients();

    /**
     * @param  id - ID number of the patient
     * @return patient instance with specified ID number
     */
    PatientDto getPatientById(Long id);

    /**
     *
     * @param id - patient ID number
     * @param patientDto - PatientDto which will be converted into Patient type instance
     * @return PatientDto instance
     */
    PatientDto updatePatient(Long id, PatientDto patientDto);

    /**
     *
     * @param id - ID number of the patient to delete
     */
    void deletePatient(Long id);

    /**
     *
     * @param patientId - ID number of patient
     * @param visitDto - visit which will be added to patient's visits
     * @return ID number of added visit
     */
    long addVisitToPatient(Long patientId, VisitDto visitDto);
}
