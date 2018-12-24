package com.anialopata.registration.api.impl;

import com.anialopata.registration.api.ApiVisitController;
import com.anialopata.registration.dto.VisitDto;
import com.anialopata.registration.dto.VisitListDto;
import com.anialopata.registration.service.VisitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ania on 2018-11-11.
 */
@Api(description = "Visit Controller")
@RestController
@RequestMapping("/api/v1/visits")
@CrossOrigin(origins = "http://localhost:4200")
public class VisitController implements ApiVisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {

        this.visitService = visitService;
    }

    @ApiOperation(value = "List all visits")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VisitListDto getAllVisits() {
        return new VisitListDto(visitService.getAllVisits());
    }

    @ApiOperation(value = "List all active visits")
    @GetMapping({"/active"})
    @ResponseStatus(HttpStatus.OK)
    public VisitListDto getAllActiveVisits() {
        return new VisitListDto(visitService.getAllActiveVisits());
    }

    @ApiOperation(value = "Get visit by ID value")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VisitDto getVisitById(@PathVariable Long id){
        return visitService.getVisitById(id);
    }

    @ApiOperation(value = "Create new visit")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VisitDto createNewVisit(@RequestBody VisitDto visitDto){
        return visitService.createVisit(visitDto);
    }

    @ApiOperation(value = "Update existing visit")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VisitDto updateVisit(@PathVariable Long id, @RequestBody VisitDto visitDto){
        return visitService.saveVisitByDto(id, visitDto);
    }

    @ApiOperation(value = "Delete visit")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteVisit(@PathVariable Long id){
        visitService.deleteVisit(id);
    }


    @ApiOperation(value = "List all visits for requested date")
    @GetMapping({"/show/date/{date}"})
    @ResponseStatus(HttpStatus.OK)
    public List<VisitDto> getVisitsByDate(@PathVariable String date) {
        return visitService.getVisitByDate(date);
    }

    @ApiOperation(value = "List all visits for requested day")
    @GetMapping({"/show/day/{day}"})
    @ResponseStatus(HttpStatus.OK)
    public List<VisitDto> getVisitsByDay(@PathVariable String day) {
        return visitService.getVisitByDay(day);
    }
}
