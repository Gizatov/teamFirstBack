package com.example.sdudent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the document
    private String docName; // Name of the document
    private String docType; // Type of the document
}
