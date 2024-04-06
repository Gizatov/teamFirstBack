package com.example.sdudent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDto {
    private int totalStudents;
    private int voters;
    private int male;
    private int female;
    private int fourCourse;
    private int threeCourse;
    private int twoCourse;
    private int oneCourse;
    private int totalCandidates;

}
