package com.anialopata.registration.service;

import com.anialopata.registration.dto.SpecialistDto;
import com.anialopata.registration.mapper.CategoryMapper;
import com.anialopata.registration.mapper.SpecialistMapper;
import com.anialopata.registration.mapper.VisitMapper;
import com.anialopata.registration.model.Specialist;
import com.anialopata.registration.repository.CategoryRepository;
import com.anialopata.registration.repository.SpecialistRepository;
import com.anialopata.registration.repository.VisitRepository;
import com.anialopata.registration.service.impl.SpecialistServiceImpl;
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
public class SpecialistServiceTest {

    @Mock
    SpecialistRepository specialistRepository;

    SpecialistMapper specialistMapper = SpecialistMapper.INSTANCE;

    SpecialistService specialistService;
    VisitMapper visitMapper = VisitMapper.INSTANCE;
    VisitRepository visitRepository;
    CategoryMapper categoryMapper;
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        specialistService = new SpecialistServiceImpl(specialistRepository, specialistMapper, visitMapper, visitRepository, categoryMapper, categoryRepository){
        };
    }

    @Test
    public void getAllSpecialistsTest() throws Exception {

        Specialist specialist1 = new Specialist();
        specialist1.setId(1l);
        specialist1.setFirstName("Anna");
        specialist1.setLastName("Lopata");

        Specialist specialist2 = new Specialist();
        specialist2.setId(2l);
        specialist2.setFirstName("Jan");
        specialist2.setLastName("Kowalski");

        when(specialistRepository.findAll()).thenReturn(Arrays.asList(specialist1, specialist2));

        List<SpecialistDto> specialistDtoList = specialistService.getAllSpecialists();

        assertEquals(2, specialistDtoList.size());

    }

    @Test
    public void getAllActiveSpecialistsTest() throws Exception {

        Specialist specialist = new Specialist();
        specialist.setId(1l);
        specialist.setFirstName("Anna");
        specialist.setLastName("Lopata");
        specialist.setActive(true);

        Specialist specialist2 = new Specialist();
        specialist2.setId(2l);
        specialist2.setFirstName("Jan");
        specialist2.setLastName("Kowalski");
        specialist2.setActive(false);

        when(specialistRepository.findByIsActiveIsTrue()).thenReturn(Arrays.asList(specialist));

        List<SpecialistDto> specialistDtoList = specialistService.getAllActiveSpecialists();

        assertEquals(1, specialistDtoList.size());

    }

    @Test
    public void saveSpecialistByDtoTest() throws Exception {

        SpecialistDto specialistDto = new SpecialistDto();
        specialistDto.setFirstName("Ania");

        Specialist saved = new Specialist();
        saved.setFirstName(specialistDto.getFirstName());
        saved.setId(1L);

        when(specialistRepository.save(any(Specialist.class))).thenReturn(saved);

        SpecialistDto savedDto = specialistService.saveSpecialistByDto(1L, specialistDto);

        assertEquals(specialistDto.getFirstName(), savedDto.getFirstName());
    }


    @Test
    public void deleteSpecialistByIdTest() throws Exception {

        Long id = 1L;
        specialistRepository.deleteById(id);

        verify(specialistRepository, times(1)).deleteById(anyLong());
    }
}
