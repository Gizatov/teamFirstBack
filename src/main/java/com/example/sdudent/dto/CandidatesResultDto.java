package com.example.sdudent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatesResultDto {
    private Long candidateId; // ID of the candidate
    private Long votesCount; // Number of votes received by the candidate
    private String candidateName; // Name of the candidate
    private String candidateLastName; // Last name of the candidate
}
