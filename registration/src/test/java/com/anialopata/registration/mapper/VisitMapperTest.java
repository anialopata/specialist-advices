package com.anialopata.registration.mapper;

import com.anialopata.registration.dto.VisitDto;
import com.anialopata.registration.model.Visit;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ania on 2018-12-13.
 */
public class VisitMapperTest {

    public static final String NOTE = "pilna porada";
    VisitMapper visitMapper = VisitMapper.INSTANCE;

    @Test
    public void visitToVisitDto() throws Exception {

        Visit visit = new Visit();
        visit.setNote(NOTE);

        VisitDto visitDto = visitMapper.visitToVisitDto(visit);

        assertEquals(NOTE, visitDto.getNote());

    }

    @Test
    public void visitDtoVisit() throws Exception {

        VisitDto visitDto = new VisitDto();
        visitDto.setNote(NOTE);

        Visit visit = visitMapper.visitDtoToVisit(visitDto);

        assertEquals(NOTE, visit.getNote());

    }


}
