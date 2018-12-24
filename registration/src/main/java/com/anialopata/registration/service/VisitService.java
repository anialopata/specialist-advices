package com.anialopata.registration.service;

import com.anialopata.registration.dto.SimpleVisitDto;
import com.anialopata.registration.dto.VisitDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by Ania on 2018-11-11.
 */
public interface VisitService {

    /**
     *
     * @return list of visits
     */
    List<VisitDto> getAllVisits();

    List<VisitDto> getAllActiveVisits();

    /**
     * @param  id - ID number of the visit
     * @return visit instance with specified ID number
     */
    VisitDto getVisitById(Long id);

    /**
     *
     * @param visitDto - saved visit
     * @return created VisitDto instance
     */
    VisitDto createVisit(VisitDto visitDto);

    /**
     *
     * @param id - ID number of the visit
     * @param visitDto - VisitDto which will be converted into Visit type instance
     * @return VisitDto instance
     */
    VisitDto saveVisitByDto(Long id, VisitDto visitDto);

    /**
     *
     * @param id - ID number of the visit to delete
     */
    void deleteVisit(Long id);

    /**
     *
     * @param date - date in String format
     * @return list of visits which are planned for requested date in LocalDateTime format
     */
    List<VisitDto> getVisitByDate(String date);

    /**
     *
     * @param day - day in String format
     * @return list of visits which are planned between start and end of requested day in LocalDateTime format
     */
    List<VisitDto> getVisitByDay(String day);

    /**
     *
     * @param day - day in LocalDateTime format
     * @param specialistId - ID number of specialist whose visits are requested
     * @return list of specialist's visits for requested day
     */
    List<SimpleVisitDto> getSpecialistVisitsByDay(LocalDateTime day, long specialistId);

    /**
     *
     * @param patientId - ID number of patient whose visits are requested
     * @return set of patient's visits
     */
    Set<VisitDto> findByPatient(Long patientId);

    /**
     *
     * @param patientId - ID number of the patient
     * @param visitDto - visit which will be added to patient's visits
     * @return ID number of added visit
     */

}
