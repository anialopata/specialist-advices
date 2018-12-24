package com.anialopata.registration.service.impl;

import com.anialopata.registration.dto.SimpleVisitDto;
import com.anialopata.registration.dto.SpecialistDto;
import com.anialopata.registration.dto.VisitDto;
import com.anialopata.registration.exception.DateIsBeforeNowException;
import com.anialopata.registration.exception.UserNotFoundException;
import com.anialopata.registration.mapper.VisitMapper;
import com.anialopata.registration.model.Patient;
import com.anialopata.registration.model.Specialist;
import com.anialopata.registration.model.Visit;
import com.anialopata.registration.repository.PatientRepository;
import com.anialopata.registration.repository.VisitRepository;
import com.anialopata.registration.service.VisitService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Ania on 2018-11-11.
 */
@Service
@Transactional
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final VisitMapper visitMapper;

    public VisitServiceImpl(VisitRepository visitRepository, VisitMapper visitMapper, PatientRepository patientRepository) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.visitMapper = visitMapper;
    }

    @Override
    public List<VisitDto> getAllActiveVisits() {
        return visitRepository
                .findByIsActiveIsTrue()
                .stream()
                .map(visit -> {
                    VisitDto visitDto = visitMapper.visitToVisitDto(visit);
                    return visitDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitDto> getAllVisits() {
        return visitRepository
                .findAll()
                .stream()
                .map(visit -> {
                    VisitDto visitDto = visitMapper.visitToVisitDto(visit);
                    return visitDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VisitDto getVisitById(Long id) {
        return visitRepository
                .findById(id)
                .map(visitMapper::visitToVisitDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public VisitDto createVisit(VisitDto visitDto) {

        Visit visit = visitMapper.visitDtoToVisit(visitDto);
        Visit saved = visitRepository.save(visit);
        VisitDto returned = visitMapper.visitToVisitDto(saved);
        returned.setVisitUrl("api/v1/visits/" + saved.getId());
        return returned;
    }

    @Override
    public VisitDto saveVisitByDto(Long id, VisitDto visitDto) {
        Visit savedVisit = visitMapper.visitDtoToVisit(visitDto);
        savedVisit.setId(id);

        return saveAndReturnDto(savedVisit);
    }

    @Override
    public void deleteVisit(Long id) {
//        Optional<Visit> visit = visitRepository.findById(id);
//        if (!visit.isPresent()) throw new UserNotFoundException("Visit doesn't exist");
        VisitDto visitDto = getVisitById(id);
        visitDto.setActive(false);
        Visit visit = visitMapper.visitDtoToVisit(visitDto);
        Visit saved = visitRepository.save(visit);
    }

    private VisitDto saveAndReturnDto(Visit visit) {
//        if(visit.getDate().isBefore(LocalDateTime.now()))
//            throw new DateIsBeforeNowException("Incorrect date");

//        else {
            Visit savedVisit = visitRepository.save(visit);
            VisitDto returnedDto = visitMapper.visitToVisitDto(savedVisit);
            returnedDto.setVisitUrl("/api/v1/visits/" + savedVisit.getId());

            return returnedDto;
    }

    @Override
    public List<VisitDto> getVisitByDate(String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);

        return visitRepository
                .findByDate(dateTime)
                .stream()
                .map(visitMapper::visitToVisitDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitDto> getVisitByDay(String day) {
        LocalDateTime startOfDay = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        LocalDateTime endOfDay = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(LocalTime.MAX);

        return visitRepository
                .findByDateIsBetween(startOfDay, endOfDay)
                .stream()
                .map(visit -> {
                    return visitMapper.visitToVisitDto(visit);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<SimpleVisitDto> getSpecialistVisitsByDay(LocalDateTime day, long specialistId) {
        LocalDateTime startOfDay = day.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = day.toLocalDate().atTime(LocalTime.MAX);

        return visitRepository
                .findBySpecialistIdAndDateIsBetween(specialistId, startOfDay, endOfDay)
                .stream()
                .map(visitMapper::visitToSimpleVisitDto)
                .collect(Collectors.toList());
    }

    @Override
    public Set<VisitDto> findByPatient(Long patientId) {
        return visitRepository
                .findByPatientId(patientId)
                .stream()
                .map(visitMapper::visitToVisitDto)
                .collect(Collectors.toSet());
    }

}
