package com.example.sdudent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateFileDto {
   private String photo;
   private String about;
   private String gpa;
   private String awards;
   private String clubs;
}
