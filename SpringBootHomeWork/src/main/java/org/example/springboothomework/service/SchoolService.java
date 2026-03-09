package org.example.springboothomework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public double showProfit() {
        double moneyEarned = courseService.getTotalMoneyEarned();
        double moneySpent = teacherService.getTotalSalary();

        return moneyEarned - moneySpent;
    }
}
