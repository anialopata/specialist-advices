package com.anialopata.registration.service;

import com.anialopata.registration.dto.PatientDto;
import com.anialopata.registration.mapper.PatientMapper;
import com.anialopata.registration.mapper.VisitMapper;
import com.anialopata.registration.model.Patient;
import com.anialopata.registration.repository.PatientRepository;
import com.anialopata.registration.repository.VisitRepository;
import com.anialopata.registration.service.impl.PatientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by Ania on 2018-11-24.
 */
public class PatientServiceTest {

    @Mock
    PatientRepository patientRepository;

    private PatientMapper patientMapper = PatientMapper.INSTANCE;

    private PatientService patientService;
    private VisitMapper visitMapper = VisitMapper.INSTANCE;
    private VisitRepository visitRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        patientService = new PatientServiceImpl(patientRepository, patientMapper, visitMapper, visitRepository);
    }

    @Test
    public void getAllPatientsTest() throws Exception {

        Patient patient1 = new Patient();
        patient1.setId(1l);
        patient1.setFirstName("Anna");
        patient1.setLastName("Lopata");

        Patient patient2 = new Patient();
        patient2.setId(2l);
        patient2.setFirstName("Jan");
        patient2.setLastName("Kowalski");

        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        List<PatientDto> patientDtoList = patientService.getAllPatients();

        assertEquals(2, patientDtoList.size());

    }

    @Test
    public void getAllActivePatientsTest() throws Exception {

        Patient patient1 = new Patient();
        patient1.setId(1l);
        patient1.setFirstName("Anna");
        patient1.setLastName("Lopata");
        patient1.setActive(true);

        Patient patient2 = new Patient();
        patient2.setId(2l);
        patient2.setFirstName("Jan");
        patient2.setLastName("Kowalski");
        patient2.setActive(false);

        when(patientRepository.findByIsActiveIsTrue()).thenReturn(Arrays.asList(patient1));

        List<PatientDto> patientDtoList = patientService.getAllActivePatients();

        assertEquals(1, patientDtoList.size());

    }


    @Test
    public void deletePatientByIdTest() throws Exception {

        Long id = 1L;
        patientRepository.deleteById(id);

        verify(patientRepository, times(1)).deleteById(anyLong());
    }
}
