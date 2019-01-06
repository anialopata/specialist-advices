package com.anialopata.registration.service.impl;

import com.anialopata.registration.dto.SpecialistDto;
import com.anialopata.registration.dto.VisitDto;
import com.anialopata.registration.exception.UserNotFoundException;
import com.anialopata.registration.mapper.CategoryMapper;
import com.anialopata.registration.mapper.SpecialistMapper;
import com.anialopata.registration.mapper.VisitMapper;
import com.anialopata.registration.model.Category;
import com.anialopata.registration.model.Patient;
import com.anialopata.registration.model.Specialist;
import com.anialopata.registration.model.Visit;
import com.anialopata.registration.repository.*;
import com.anialopata.registration.service.SpecialistService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Created by Ania on 2018-11-11.
 */
@Service
@Transactional
public class SpecialistServiceImpl implements SpecialistService {

    private final SpecialistRepository specialistRepository;
    private final SpecialistMapper specialistMapper;
    private final VisitMapper visitMapper;
    private final VisitRepository visitRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final PatientRepository patientRepository;

    public SpecialistServiceImpl(SpecialistRepository specialistRepository, SpecialistMapper specialistMapper, VisitMapper visitMapper, VisitRepository visitRepository, CategoryMapper categoryMapper, CategoryRepository categoryRepository, PatientRepository patientRepository) {
        this.specialistRepository = specialistRepository;
        this.specialistMapper = specialistMapper;
        this.visitMapper = visitMapper;
        this.visitRepository = visitRepository;
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<SpecialistDto> getAllActiveSpecialists() {
        return specialistRepository
                .findByIsActiveIsTrue()
                .stream()
                .map(specialist -> {
                    SpecialistDto specialistDto = specialistMapper.specialistToSpecialistDto(specialist);
                    return specialistDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<SpecialistDto> getAllSpecialists() {
        return specialistRepository
                .findAll()
                .stream()
                .map(specialist -> {
                    return specialistMapper.specialistToSpecialistDto(specialist);
                })
                .collect(Collectors.toList());
    }

    @Override
    public SpecialistDto getSpecialistById(Long id) {
        return specialistRepository
                .findById(id)
                .map(specialistMapper::specialistToSpecialistDto)
                .orElseThrow(() -> new UserNotFoundException("Specialist doesn't exist"));
    }

    @Override
    public SpecialistDto createSpecialist(SpecialistDto specialistDto) {
        if(specialistDto.getCategory() != null) {
            Category category = categoryRepository.findByName(specialistDto.getCategory().getName());
            if (category != null)
                specialistDto.setCategory(category);
        }
        Specialist specialist= specialistMapper.specialistDtoToSpecialist(specialistDto);
        Specialist saved = specialistRepository.save(specialist);
        SpecialistDto returned = specialistMapper.specialistToSpecialistDto(saved);
        returned.setSpecialistUrl("/api/v1/specialists/" + saved.getId());
        return returned;
    }

    @Override
    public SpecialistDto saveSpecialistByDto(Long id, SpecialistDto specialistDto) {
        Specialist savedSpecialist = specialistMapper.specialistDtoToSpecialist(specialistDto);
        savedSpecialist.setId(id);
        savedSpecialist.setActive(true);
        return saveAndReturnDto(savedSpecialist);
    }

    private SpecialistDto saveAndReturnDto(Specialist specialist) {

        Specialist saved = specialistRepository.save(specialist);
        SpecialistDto returnedDto = specialistMapper.specialistToSpecialistDto(specialist);
        returnedDto.setSpecialistUrl("/api/v1/specialists/" + saved.getId());
        returnedDto.setActive(true);
        return returnedDto;
}


    @Override
    public void deleteSpecialist(Long id) {
//        Optional<Specialist> specialist = specialistRepository.findById(id);
//        if (!specialist.isPresent()) throw new UserNotFoundException("Specialist doesn't exist");
//        specialistRepository.deleteById(id);
        SpecialistDto specialistDto = getSpecialistById(id);
        specialistDto.setActive(false);

        Specialist specialist = specialistMapper.specialistDtoToSpecialist(specialistDto);
        Specialist saved = specialistRepository.save(specialist);
    }


    @Override
    public Set<VisitDto> findSpecialistVisits(Long id) {

        SpecialistDto specialistDto = getSpecialistById(id);
        Specialist specialist = specialistMapper.specialistDtoToSpecialist(specialistDto);
        SpecialistDto returned = specialistMapper.specialistToSpecialistDto(specialist);

        return returned.getVisits();
    }

    @Override
    public long addVisitToSpecialist(Long specialistId, VisitDto visitDto, Long patientId) {

//        if (!isSpecialistAvailable(specialistId, visitDto))
//            throw new SpecialistNotAvailableException("Specialist with id = " + specialistId + " is not available at this time");

        Specialist specialist = specialistRepository.getOne(specialistId);
        Patient patient = patientRepository.getOne(patientId);
        Visit visit = visitMapper.visitDtoToVisit(visitDto);
        visit.setSpecialist(specialist);
        visit.setPatient(patient);
        visit.setCategory(specialist.getCategory());
        visitRepository.save(visit);
        specialist.addVisit(visit);
        specialistRepository.save(specialist);
        patientRepository.save(patient);

        return visit.getId();
    }

    // (StartA <= KoniecB) && (KoniecA >= StartB)
//    private boolean isSpecialistAvailable(Long specialistId, VisitDto visitDto) {
//       return visitRepository.findBySpecialistIdAndDateIsBetween(specialistId, visitDto.getDate().toLocalDate().atStartOfDay(), visitDto.getDate().toLocalDate().atTime(LocalTime.MAX))
//                .stream()
//                .filter(visit -> visit.getDate().isBefore(visitDto.getDate().plusMinutes(visitDto.getDuration())))
//                .filter(visit -> visit.getDate().plusMinutes(visit.getDuration()).isAfter(visitDto.getDate()))
//                .collect(Collectors.toList()).isEmpty();
//    }


    @Override
    public void addSpecialistToCategory(long categoryId, SpecialistDto specialistDto) {

        Category category = categoryRepository.getOne(categoryId);
        Specialist specialist = specialistMapper.specialistDtoToSpecialist(specialistDto);
        specialistRepository.save(specialist);
        category.addSpecialist(specialist);
        categoryRepository.save(category);
    }
}
