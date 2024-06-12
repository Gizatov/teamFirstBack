package com.example.sdudent.repository;

import com.example.sdudent.entity.UserDocumentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentFileRepository extends JpaRepository<UserDocumentFile, Long> {

}
