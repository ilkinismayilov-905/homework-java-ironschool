package org.example.springboothomework.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class Teacher {
    private static int counter = 1;

    private String teacherId;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Positive(message = "Salary must be greater than 0")
    private double salary;

    public Teacher(String name, double salary) {
        this.teacherId = String.valueOf(counter++);
        this.name = name;
        this.salary = salary;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(@NotBlank(message = "Name cannot be blank") String name) {
        this.name = name;
    }

    public void setSalary(@Positive(message = "Salary must be greater than 0") double salary) {
        this.salary = salary;
    }
}
