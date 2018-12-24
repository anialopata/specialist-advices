package com.anialopata.registration.service;

import com.anialopata.registration.dto.PatientDto;
import com.anialopata.registration.dto.VisitDto;
import com.anialopata.registration.mapper.PatientMapper;
import com.anialopata.registration.mapper.SpecialistMapper;
import com.anialopata.registration.mapper.VisitMapper;
import com.anialopata.registration.model.Patient;
import com.anialopata.registration.model.Visit;
import com.anialopata.registration.repository.PatientRepository;
import com.anialopata.registration.repository.SpecialistRepository;
import com.anialopata.registration.repository.VisitRepository;
import com.anialopata.registration.service.impl.PatientServiceImpl;
import com.anialopata.registration.service.impl.VisitServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ania on 2018-12-14.
 */
public class VisitServiceTest {

    @Mock
    VisitRepository visitRepository;

    VisitMapper visitMapper = VisitMapper.INSTANCE;

    VisitService visitService;
    PatientRepository patientRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        visitService = new VisitServiceImpl(visitRepository, visitMapper, patientRepository);
    }

    @Test
    public void getAllVisitsTest() throws Exception {

        Visit visit1 = new Visit();
        visit1.setId(1l);
        visit1.setNote("bcbcbcbcb");

        Visit visit2 = new Visit();
        visit2.setId(2l);
        visit2.setNote("mmmm");
        when(visitRepository.findAll()).thenReturn(Arrays.asList(visit1, visit2));

        List<VisitDto> visitDtoList = visitService.getAllVisits();

        assertEquals(2, visitDtoList.size());

    }

    @Test
    public void getAllActiveVisitsTest() throws Exception {

        Visit visit1 = new Visit();
        visit1.setId(1l);
        visit1.setNote("bcbcbcbcb");
        visit1.setActive(true);

        Visit visit2 = new Visit();
        visit2.setId(2l);
        visit2.setNote("mmmm");
        visit2.setActive(false);

        when(visitRepository.findAll()).thenReturn(Arrays.asList(visit1));

        List<VisitDto> visitDtoList = visitService.getAllVisits();

        assertEquals(1, visitDtoList.size());

    }

    @Test
    public void createNewVisitTest() throws Exception {

        VisitDto visitDto = new VisitDto();
        visitDto.setNote("notatka");

        Visit savedVisit= new Visit();
        savedVisit.setNote(visitDto.getNote());
        savedVisit.setId(1L);

        when(visitRepository.save(any(Visit.class))).thenReturn(savedVisit);

        VisitDto savedDto = visitService.saveVisitByDto(1L, visitDto);

        assertEquals(visitDto.getNote(), savedDto.getNote());

    }

    @Test
    public void deleteVisittByIdTest() throws Exception {

        Long id = 1L;
        visitRepository.deleteById(id);

        verify(visitRepository, times(1)).deleteById(anyLong());
    }
}
