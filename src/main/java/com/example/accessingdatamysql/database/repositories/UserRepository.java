package com.example.accessingdatamysql.database.repositories;

import com.example.accessingdatamysql.database.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}