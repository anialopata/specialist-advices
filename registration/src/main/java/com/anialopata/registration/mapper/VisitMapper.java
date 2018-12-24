package com.anialopata.registration.mapper;

import com.anialopata.registration.dto.SimpleVisitDto;
import com.anialopata.registration.dto.VisitDto;
import com.anialopata.registration.model.Visit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Ania on 2018-11-11.
 */
@Mapper
public interface VisitMapper {
    VisitMapper INSTANCE = Mappers.getMapper(VisitMapper.class);
    VisitDto visitToVisitDto(Visit visit);
    Visit visitDtoToVisit(VisitDto visitDto);
    SimpleVisitDto visitToSimpleVisitDto(Visit visit);
}
