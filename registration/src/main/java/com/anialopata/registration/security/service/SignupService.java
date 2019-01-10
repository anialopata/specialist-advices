package com.anialopata.registration.security.service;

/**
 * Created by Ania on 2018-11-15.
 */
import com.anialopata.registration.dto.SpecialistDto;
import com.anialopata.registration.mapper.SpecialistMapper;
import com.anialopata.registration.model.Category;
import com.anialopata.registration.model.Specialist;
import com.anialopata.registration.model.User;
import com.anialopata.registration.model.UserRole;
import com.anialopata.registration.repository.CategoryRepository;
import com.anialopata.registration.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@javax.transaction.Transactional
public class SignupService {

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final PasswordEncoder passwordEncoder;

    private final SpecialistMapper specialistMapper;

    public SignupService(UserRepository userRepository, CategoryRepository categoryRepository, PasswordEncoder passwordEncoder, SpecialistMapper specialistMapper) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.passwordEncoder = passwordEncoder;
        this.specialistMapper = specialistMapper;
    }

    @Transactional
    public void addPatient(User user) {
        user.setRoles(Collections.singletonList(new UserRole("PATIENT")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void addSpecialist(SpecialistDto specialistDto) {
        Category category = categoryRepository.findById(specialistDto.getCategory().getId()).get();
        Specialist user = specialistMapper.specialistDtoToSpecialist(specialistDto);
        user.setCategory(category);
        user.setRoles(Collections.singletonList(new UserRole("SPECIALIST")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}


