package com.anialopata.registration.repository;

import com.anialopata.registration.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ania on 2018-11-02.
 */
public interface PatientRepository extends JpaRepository<Patient, Long>  {

    List<Patient> findByIsActiveIsTrue();
}
