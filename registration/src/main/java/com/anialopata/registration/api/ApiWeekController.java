package com.anialopata.registration.api;

import com.anialopata.registration.dto.SimpleVisitDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;


public interface ApiWeekController {

    //TODO: stuff ze swaggera i tak dla każdego controllera, zrób porządną dokumentację
    @ResponseStatus(OK)
    Map<DayOfWeek, List<SimpleVisitDto>> getSpecialistWeek(@PathVariable long specialistId, @PathVariable String date, int weekNo);

}
