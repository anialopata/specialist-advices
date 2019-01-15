package com.anialopata.registration.api.impl;

import com.anialopata.registration.api.ApiWeekController;
import com.anialopata.registration.dto.SimpleVisitDto;
import com.anialopata.registration.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/v1/weeks/")
@CrossOrigin(origins = "http://localhost:4200")
public class WeekController implements ApiWeekController {

    @Autowired
    private VisitService visitService;

    @GetMapping("/{specialistId}/{date}/{weekNo}")
    public Map<DayOfWeek, List<SimpleVisitDto>> getSpecialistWeek(@PathVariable long specialistId, @PathVariable String date, @PathVariable int weekNo) {

        Map<DayOfWeek, List<SimpleVisitDto>> week = new LinkedHashMap<>();
        IntStream.rangeClosed(0,6).forEach(day -> {
            LocalDateTime currentDate = LocalDate   .parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                                                    .atStartOfDay()
                                                    .plusWeeks(weekNo)
                                                    .plusDays(day);
            week.put(currentDate.getDayOfWeek(), visitService.getSpecialistVisitsByDay(currentDate, specialistId));
        });

        return week;
    }
}
