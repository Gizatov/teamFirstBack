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

    public StatisticDto getStatistic() {
        int maleCount = countUsersByGender("male" ,3 );
        int femaleCount = countUsersByGender("female",3);

        int roleId = countUsersByRole(3);

        int roleFourCount = countUsersByCourse(String.valueOf(4),3);
        int roleThreeCount = countUsersByCourse(String.valueOf(3),3);
        int roleTwoCount = countUsersByCourse(String.valueOf(2),3);
        int roleOneCount = countUsersByCourse(String.valueOf(1),3);

        int totalVoters = countUniqueStudentIds();
        int totalCandidates = countUsersByRole(2);

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

    private int countUsersByGender(String gender , int roleId ) {
        String sql = "SELECT COUNT(*) FROM Users WHERE gender = ? AND role_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, gender,roleId);
    }

    private int countUsersByRole(int roleId) {
        String sql = "SELECT COUNT(*) FROM Users WHERE role_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, roleId);
    }
    private int countUsersByCourse(String course, int roleId) {
        String sql = "SELECT COUNT(*) FROM Users WHERE faculty = ? AND role_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, course,roleId);
    }
    private int countUniqueStudentIds() {
        String sql = "SELECT COUNT(DISTINCT student_id) FROM students_voice";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }



}
