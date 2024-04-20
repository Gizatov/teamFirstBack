package com.example.sdudent.service;

import com.example.sdudent.dto.StatisticDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StatisticService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method to retrieve statistical data
    public StatisticDto getStatistic() {
        // Count the number of male users with role ID 3 (students)
        int maleCount = countUsersByGender("male", 3);
        // Count the number of female users with role ID 3 (students)
        int femaleCount = countUsersByGender("female", 3);

        // Count the total number of students (users) with role ID 3
        int roleId = countUsersByRole(3);

        // Count the number of students in each course with role ID 3
        int roleFourCount = countUsersByCourse(4, 3);
        int roleThreeCount = countUsersByCourse(3, 3);
        int roleTwoCount = countUsersByCourse(2, 3);
        int roleOneCount = countUsersByCourse(1, 3);

        // Count the total number of unique student IDs (voters)
        int totalVoters = countUniqueStudentIds();
        // Count the total number of candidates with role ID 2
        int totalCandidates = countUsersByRole(2);

        // Create a new StatisticDto object and set the retrieved counts
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setMale(maleCount);
        statisticDto.setFemale(femaleCount);
        statisticDto.setFourCourse(roleFourCount);
        statisticDto.setThreeCourse(roleThreeCount);
        statisticDto.setTwoCourse(roleTwoCount);
        statisticDto.setOneCourse(roleOneCount);
        statisticDto.setTotalStudents(roleId);
        statisticDto.setVoters(totalVoters);
        statisticDto.setTotalCandidates(totalCandidates);

        return statisticDto;
    }

    // Method to count users by gender and role ID
    private int countUsersByGender(String gender, int roleId) {
        String sql = "SELECT COUNT(*) FROM Users WHERE gender = ? AND role_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, gender, roleId);
    }

    // Method to count users by role ID
    private int countUsersByRole(int roleId) {
        String sql = "SELECT COUNT(*) FROM Users WHERE role_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, roleId);
    }

    // Method to count users by course and role ID
    private int countUsersByCourse(int course, int roleId) {
        String sql = "SELECT COUNT(*) FROM Users WHERE course = ? AND role_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, course, roleId);
    }

    // Method to count unique student IDs (voters)
    private int countUniqueStudentIds() {
        String sql = "SELECT COUNT(DISTINCT student_id) FROM students_voice";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
