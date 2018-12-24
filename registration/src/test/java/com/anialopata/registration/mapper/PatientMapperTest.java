package com.anialopata.registration.mapper;

import com.anialopata.registration.dto.PatientDto;
import com.anialopata.registration.model.Patient;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
/**
 * Created by Ania on 2018-11-23.
 */
public class PatientMapperTest {

    public static final String FIRSTNAME ="Ania";
    public static final String LASTNAME = "Lopata";
    PatientMapper patientMapper = PatientMapper.INSTANCE;

    @Test
    public void patientToPatientDto() throws Exception {

        Patient patient = new Patient();
        patient.setFirstName(FIRSTNAME);
        patient.setLastName(LASTNAME);

        PatientDto patientDto = patientMapper.patientToPatientDTO(patient);

        assertEquals(FIRSTNAME, patientDto.getFirstName());
        assertEquals(LASTNAME, patientDto.getLastName());
    }

    @Test
    public void patientDtoToPatient() throws Exception {

        PatientDto patientDto = new PatientDto();
        patientDto.setFirstName(FIRSTNAME);
        patientDto.setLastName(LASTNAME);

        Patient patient = patientMapper.patientDTOToPatient(patientDto);

        assertEquals(FIRSTNAME, patient.getFirstName());
        assertEquals(LASTNAME, patient.getLastName());
    }
}
