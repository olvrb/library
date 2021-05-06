package com.oliver.library.Application.Repositories;

import com.oliver.library.Application.Entities.User.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, String> {

    Set<User> findByName(String name);

    User findFirstByName(String name);

    Optional<User> findBySsn(String ssn);
}