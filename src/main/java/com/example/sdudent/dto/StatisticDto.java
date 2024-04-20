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
    private int totalStudents; // Total number of students
    private int voters; // Number of students who have voted
    private int male; // Number of male students
    private int female; // Number of female students
    private int fourCourse; // Number of students in the fourth course
    private int threeCourse; // Number of students in the third course
    private int twoCourse; // Number of students in the second course
    private int oneCourse; // Number of students in the first course
    private int totalCandidates; // Total number of candidates in the election
}
