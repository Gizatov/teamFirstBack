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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_document_file_id")
    private UserDocumentFile userDocumentFile;

    private boolean correct;
    private String notCorrectReason;
    private String commissionName;
    private String commissionSurname;
}

