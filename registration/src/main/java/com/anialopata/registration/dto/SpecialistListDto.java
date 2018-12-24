package com.anialopata.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Ania on 2018-11-02.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialistListDto {

    List<SpecialistDto> specialists;

}
