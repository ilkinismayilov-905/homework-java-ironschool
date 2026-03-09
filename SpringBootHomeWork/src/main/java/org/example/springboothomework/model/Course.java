package org.example.springboothomework.model;

import jakarta.validation.constraints.*;

public class Course {
    private static int counter = 1;
    private String courseId;

    @NotBlank(message = "Course name cannot be empty")
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
    private String name;

    @Min(value = 0, message = "Price cannot be negative")
    private Double price;

    @Min(value = 0, message = "Money earned cannot be negative")
    private Double money_earned;

//    @NotNull(message = "Teacher must be provided if assigning")
    private String teacherId;

//    @NotNull(message = "Student must be provided if assigning")
    private String studentId;


    public Course(String name, double price) {
        this.courseId = String.valueOf(counter++);
        this.name = name;
        this.price = price;
    }

    public Course() {
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Course.counter = counter;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMoney_earned() {
        return money_earned;
    }

    public void setMoney_earned(Double money_earned) {
        this.money_earned = money_earned;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}