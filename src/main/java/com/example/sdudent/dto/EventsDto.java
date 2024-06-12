package com.example.sdudent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventsDto {
    private Long id;
    private Date dateRegStart;
    private Date dateRegEnd;
    private int countCandidate;
    private Date dateEventStart;
    private Date dateEventEnd;
}
