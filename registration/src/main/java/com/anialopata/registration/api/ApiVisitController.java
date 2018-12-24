package com.anialopata.registration.api;

import com.anialopata.registration.dto.VisitDto;
import com.anialopata.registration.dto.VisitListDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by Ania on 2018-12-04.
 */
public interface ApiVisitController {

    @ResponseStatus(OK)
    VisitListDto getAllVisits();

    @ResponseStatus(OK)
    VisitListDto getAllActiveVisits();

    @ResponseStatus(OK)
    VisitDto getVisitById(@PathVariable Long id);

    @ResponseStatus(OK)
    VisitDto createNewVisit(@RequestBody VisitDto visitDto);

    @ResponseStatus(OK)
    VisitDto updateVisit(@PathVariable Long id, @RequestBody VisitDto visitDto);

    @ResponseStatus(OK)
    void deleteVisit(@PathVariable Long id);

    @ResponseStatus(OK)
    List<VisitDto> getVisitsByDate(@PathVariable String date);

    @ResponseStatus(OK)
    List<VisitDto> getVisitsByDay(@PathVariable String day);



}
