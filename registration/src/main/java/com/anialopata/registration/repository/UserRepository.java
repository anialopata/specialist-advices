package com.anialopata.registration.repository;

/**
 * Created by Ania on 2018-11-16.
 */

import com.anialopata.registration.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByIsActiveTrueAndUsername(String username);
}
