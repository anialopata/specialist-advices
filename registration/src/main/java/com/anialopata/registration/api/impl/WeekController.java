package com.anialopata.registration.api.impl;

import com.anialopata.registration.api.ApiWeekController;
import com.anialopata.registration.dto.SimpleVisitDto;
import com.anialopata.registration.service.PatientService;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/v1/weeks/")
@CrossOrigin(origins = "http://localhost:4200")
public class WeekController implements ApiWeekController {

    @Autowired
    private VisitService visitService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/{specialistId}/{date}/{weekNo}")
    public Map<DayOfWeek, List<SimpleVisitDto>> getSpecialistWeek(@PathVariable long specialistId, @PathVariable String date, @PathVariable int weekNo) {

        Map<DayOfWeek, List<SimpleVisitDto>> week = new LinkedHashMap<>();
        IntStream.rangeClosed(0,6).forEach(day -> {
            LocalDateTime currentDate = LocalDate   .parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                                                    .atStartOfDay()
                                                    .plusWeeks(weekNo) //dzieki temu moge mogla skakac strzaleczka w prawo i w lewo :) np -1, 0 , 1
                                                    .plusDays(day); //jako pierwszy dzien przedstawiany jest zawsze ten bieżący
            week.put(currentDate.getDayOfWeek(), visitService.getSpecialistVisitsByDay(currentDate, specialistId));
        });

        return week;
    }


// w mapie musialabym zwrocic dayofweek, adrugie to nie lista tylko obiekt ktory zaweira date tego dnia i liste

    // endpoint ktory przyjmie id spec i date (dzisiejsza!), podam week no, i jak klikne w ta wizyte jak ja zarezerwuje, to dostane konkretny daYOfWeek
    // i dodatkowo godzine zarezerwowana.


//    @GetMapping("/{patientId}/{date}/{weekNo}")
//    public Map<DayOfWeek, List<SimpleVisitDto>> getPatientWeek(@PathVariable long patientId, @PathVariable String date, @PathVariable long weekNo) {
//
//        Map<DayOfWeek, List<SimpleVisitDto>> week = new ConcurrentHashMap<>();
//        IntStream.rangeClosed(0,6).forEach(day -> {
//            LocalDateTime currentDate = LocalDate   .parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
//                    .atStartOfDay()
//                    .plusWeeks(weekNo) //dzieki temu moge mogla skakac strzaleczka w prawo i w lewo :) np -1, 0 , 1
//                    .plusDays(day); //jako pierwszy dzien przedstawiany jest zawsze ten bieżący
//            week.put(currentDate.getDayOfWeek(), patientService.getPatientVisitsByDay(currentDate, patientId));
//        });
//
//        return week;
//    }
}
