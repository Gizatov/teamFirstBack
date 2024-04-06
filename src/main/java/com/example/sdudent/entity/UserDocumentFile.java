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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private DocumentList documentList;
    private String fileId;
}

