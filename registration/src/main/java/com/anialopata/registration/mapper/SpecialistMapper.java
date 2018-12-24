package com.anialopata.registration.mapper;

import com.anialopata.registration.dto.SpecialistDto;
import com.anialopata.registration.model.Specialist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Ania on 2018-11-02.
 */
@Mapper
public interface SpecialistMapper {
    SpecialistMapper INSTANCE = Mappers.getMapper(SpecialistMapper.class);
    SpecialistDto specialistToSpecialistDto(Specialist specialist);
    Specialist specialistDtoToSpecialist(SpecialistDto specialistDto);

}
