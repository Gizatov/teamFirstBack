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
    private Long candidateId;
    private Long votesCount;
    private String candidateName;
    private String candidateLastName;
}
