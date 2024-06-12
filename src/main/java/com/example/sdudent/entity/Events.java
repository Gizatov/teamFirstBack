package com.example.sdudent.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the user

    @Column(name = "reg_start")
    private Date registrationStart;

    @Column(name = "reg_end")
    private Date registrationEnd;

    @Column(name = "persons")
    private int persons;

    @Column(name = "chois_start")
    private Date choiceStart;

    @Column(name = "chois_end")
    private Date choiceEnd;
}
