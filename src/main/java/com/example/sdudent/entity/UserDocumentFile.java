package com.example.sdudent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "users_document_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDocumentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the user document file

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Reference to the user associated with the document file

    private String photo;
    private String about;
    private String gpa;
    private String awards;
    private String clubs;
}
