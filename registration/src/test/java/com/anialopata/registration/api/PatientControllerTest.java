package com.anialopata.registration.api;

import com.anialopata.registration.api.impl.PatientController;
import com.anialopata.registration.dto.PatientDto;
import com.anialopata.registration.service.PatientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by Ania on 2018-11-23.
 */
public class PatientControllerTest extends AbstractRestControllerTest {

    @Mock
    PatientService patientService;

    @InjectMocks
    PatientController patientController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    public void testGetAllPatients() throws Exception {

        PatientDto patientDto1 = new PatientDto();
        patientDto1.setFirstName("Ania");
        patientDto1.setLastName("Lopata");
        patientDto1.setPatientUrl("/api/v1/patients/1");

        PatientDto patientDto2 = new PatientDto();
        patientDto2.setFirstName("Jan");
        patientDto2.setLastName("Kowalski");
        patientDto2.setPatientUrl("/api/v1/patients/2");

        when(patientService.getAllPatients()).thenReturn(Arrays.asList(patientDto1, patientDto2));

        mockMvc.perform(get("/api/v1/patients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patients", hasSize(2)));
    }

    @Test
    public void testGetPatientById() throws Exception {

        PatientDto patientDto1 = new PatientDto();
        patientDto1.setFirstName("Ania");
        patientDto1.setLastName("Lopata");
        patientDto1.setId(1L);

        when(patientService.getPatientById(anyLong())).thenReturn(patientDto1);

        mockMvc.perform(get("/api/v1/patients/" + patientDto1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Ania")));
    }

    @Test
    public void testUpdatePatient() throws Exception {

        PatientDto patientDto1 = new PatientDto();
        patientDto1.setFirstName("Ania");
        patientDto1.setLastName("Lopata");

        PatientDto returnDto = new PatientDto();
        returnDto.setFirstName(patientDto1.getFirstName());
        returnDto.setLastName(patientDto1.getLastName());
        returnDto.setPatientUrl("/api/v1/patients/1");

        when(patientService.savePatientByDto(anyLong(), any(PatientDto.class))).thenReturn(returnDto);

        mockMvc.perform(put("api/v1/patients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(patientDto1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Ania")))
                .andExpect(jsonPath("$.lastName", equalTo("Lopata")))
                .andExpect(jsonPath("$.patient_url", equalTo("/api/v1/patients/1")));
    }

    @Test
    public void testDeletePatient() throws Exception {
        mockMvc.perform(delete("api/v1/patients/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(patientService).deletePatient(anyLong());
    }
}