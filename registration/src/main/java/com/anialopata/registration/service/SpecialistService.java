package com.anialopata.registration.service;

import com.anialopata.registration.dto.SpecialistDto;
import com.anialopata.registration.dto.VisitDto;

import java.util.List;
import java.util.Set;

/**
 * Created by Ania on 2018-11-02.
 */
public interface SpecialistService  {

    /**
     *
     * @return list of patients
     */
    List<SpecialistDto> getAllActiveSpecialists();


    List<SpecialistDto> getAllSpecialists();
    /**
     * @param  id - ID number of the patient
     * @return patient instance with specified ID number
     */
    SpecialistDto getSpecialistById(Long id);

    /**
     *
     * @param specialistDto - saved specialist
     * @return created SpecialistDto instance
     */
    SpecialistDto createSpecialist(SpecialistDto specialistDto);

    /**
     *
     * @param id - ID number of the specialist
     * @param specialistDto - SpecialistDto which will be converted into Specialist type instance
     * @return SpecialistDto instance
     */
    SpecialistDto saveSpecialistByDto(Long id, SpecialistDto specialistDto);

    /**
     *
     * @param id - ID number of the specialist to delete
     */
    void deleteSpecialist(Long id);

    /**
     *
     * @param id - ID number of the specialist whose visits we want to return
     * @return set of specialist's visit
     */
    Set<VisitDto> findSpecialistVisits(Long id);

    /**
     *
     * @param specialistId - ID number of the specialist
     * @param visitDto - visit which will be added to specialist's visits
     * @return ID number of added visit
     */
    long addVisitToSpecialist(Long specialistId, VisitDto visitDto);

    void addSpecialistToCategory(long categoryId, SpecialistDto specialistDto);
}
