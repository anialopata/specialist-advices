package com.anialopata.registration.repository;

import com.anialopata.registration.model.Category;
import com.anialopata.registration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ania on 2018-11-28.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    List<Category> findByIsActiveIsTrue();

}

