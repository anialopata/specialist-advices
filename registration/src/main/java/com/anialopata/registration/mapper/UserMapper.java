package com.anialopata.registration.mapper;

import com.anialopata.registration.dto.UserDto;
import com.anialopata.registration.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Ania on 2018-11-02.
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto personToPersonDTO(User user);
    User personDTOToPerson(UserDto userDto);
}
