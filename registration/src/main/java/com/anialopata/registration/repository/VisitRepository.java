package com.anialopata.registration.repository;

import com.anialopata.registration.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by Ania on 2018-11-11.
 */
public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByDate(LocalDateTime date);
    List<Visit> findByDateIsBetween(LocalDateTime from, LocalDateTime to);
    List<Visit> findBySpecialistIdAndDateIsBetween(long id, LocalDateTime from, LocalDateTime to);
    List<Visit> findByPatientIdAndDateIsBetween(long id, LocalDateTime from, LocalDateTime to);
    Set<Visit> findByPatientId(long patientId);
    Set<Visit> findByIsActiveIsTrue();
}





