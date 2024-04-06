package com.example.sdudent.repository;

import com.example.sdudent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    List<User> findAll();
    @Query(value = "SELECT * FROM users\n" +
            "where role_id = 2", nativeQuery = true)
    List<User> findAllCandidate();

    User findById(Long id);


}
