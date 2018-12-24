package com.anialopata.registration.mapper;

import com.anialopata.registration.dto.PatientDto;
import com.anialopata.registration.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Ania on 2018-11-02.
 */
@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);
    Patient patientDTOToPatient(PatientDto patientDto);
    PatientDto patientToPatientDTO(Patient patient);
}
