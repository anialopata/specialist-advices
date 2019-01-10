package com.anialopata.registration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;


/**
 * Created by Ania on 2018-11-02.
 */
//@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto extends UserDto {

    private Set<VisitDto> visits;

    @JsonProperty("patient_url")
    private String patientUrl;
}
