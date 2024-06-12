package com.example.sdudent.repository;

import com.example.sdudent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Method to find a user by email
    Optional<User> findByEmail(String email);

    // Method to find all users
    List<User> findAll();

    // Custom native query method to find all candidates
    @Query(value = "SELECT * FROM users\n" +
            "where role_id = 2 AND event_id is not null", nativeQuery = true)
    List<User> findAllCandidate();

    // Method to find a user by ID
    User findById(Long id);
}
