package org.example.springboothomework.model;

import jakarta.validation.constraints.*;

public class Course {
    private static int counter = 1;

    @NotBlank(message = "Course name cannot be empty")
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
    private String name;

    @Min(value = 0, message = "Price cannot be negative")
    private double price;

    @Min(value = 0, message = "Money earned cannot be negative")
    private double money_earned;

    @NotNull(message = "Teacher must be provided if assigning")
    private Teacher teacher;

    public Course(String name, double price) {
        this.courseId = String.valueOf(counter++);
        this.name = name;
        this.price = price;
    }


    public String getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getMoney_earned() {
        return money_earned;
    }

    public Teacher getTeacher() {
        return teacher;
    }


    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMoney_earned(double money_earned) {
        this.money_earned = money_earned;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}