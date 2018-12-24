package com.anialopata.registration.api;

import com.anialopata.registration.dto.SpecialistDto;
import com.anialopata.registration.dto.SpecialistListDto;
import com.anialopata.registration.dto.VisitDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by Ania on 2018-12-04.
 */
public interface ApiSpecialistController {

    @ResponseStatus(OK)
    SpecialistListDto getAllSpecialists(Authentication authentication);

    @ResponseStatus(OK)
    SpecialistListDto getActiveSpecialists();

    @ResponseStatus(OK)
    SpecialistDto getSpecialistById(@PathVariable Long id);

    @ResponseStatus(OK)
    SpecialistDto createNewSpecialist(@RequestBody SpecialistDto specialistDto);

    @ResponseStatus(OK)
    SpecialistDto updateSpecialist(@PathVariable Long id, @RequestBody SpecialistDto specialistDto);

    @ResponseStatus(OK)
    void deleteSpecialist(@PathVariable Long id);

    @ResponseStatus(OK)
    Set<VisitDto> findSpecialistVisits(@PathVariable Long specialistId);

    @ResponseStatus(OK)
    long addVisitToSpecialist(@PathVariable Long specialistId, @RequestBody VisitDto visitDto);
}
