package com.example.sdudent.repository;

import com.example.sdudent.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventsRepository extends JpaRepository<Events, Long> {
    Optional<Events> findById(Long id);
    Events findAllById(Long id);
    List<Events> findAll();

    void deleteAllById(Long id);
}
