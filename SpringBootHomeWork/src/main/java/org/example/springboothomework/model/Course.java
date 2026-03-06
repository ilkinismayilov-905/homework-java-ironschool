package org.example.springboothomework.model;

public class Course {
    private static int counter = 1;

    private String courseId;
    private String name;
    private double price;
    private double money_earned;
    private Teacher teacher; // nullable

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