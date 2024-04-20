package com.example.sdudent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "document_verification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the verification record

    @ManyToOne
    @JoinColumn(name = "users_document_file_id")
    private UserDocumentFile userDocumentFile; // Reference to the user document file being verified

    private boolean correct; // Indicates whether the document is correct or not
    private String notCorrectReason; // Reason for the document being not correct
    private String commissionName; // Name of the commission verifying the document
    private String commissionSurname; // Surname of the commission verifying the document
}
