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
    private Long id;
    private String docName;
    private String docType;
}


