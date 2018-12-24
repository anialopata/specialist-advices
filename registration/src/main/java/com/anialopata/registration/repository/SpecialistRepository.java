package com.anialopata.registration.repository;

import com.anialopata.registration.model.Specialist;
import com.anialopata.registration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by Ania on 2018-11-02.
 */
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    Set<Specialist> findByCategoryId(Long id);
    List<Specialist> findByIsActiveIsTrue();
}
