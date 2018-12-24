package com.anialopata.registration.mapper;

import com.anialopata.registration.dto.SpecialistDto;
import com.anialopata.registration.model.Specialist;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ania on 2018-12-13.
 */
public class SpecialistMapperTest {

    public static final String FIRSTNAME ="Jan";
    public static final String LASTNAME = "Kowalski";
    SpecialistMapper specialistMapper = SpecialistMapper.INSTANCE;

    @Test
    public void specialistToSpecialistDto() throws Exception {

        Specialist specialist = new Specialist();
        specialist.setFirstName(FIRSTNAME);
        specialist.setLastName(LASTNAME);

        SpecialistDto specialistDto = specialistMapper.specialistToSpecialistDto(specialist);

        assertEquals(FIRSTNAME, specialistDto.getFirstName());
        assertEquals(LASTNAME, specialistDto.getLastName());
    }

    @Test
    public void specialistDtoToSpecialist() throws Exception {

        SpecialistDto specialistDto = new SpecialistDto();
        specialistDto.setFirstName(FIRSTNAME);
        specialistDto.setLastName(LASTNAME);

        Specialist specialist = specialistMapper.specialistDtoToSpecialist(specialistDto);

        assertEquals(FIRSTNAME, specialist.getFirstName());
        assertEquals(LASTNAME, specialist.getLastName());
    }
}
