package org.example.springboothomework.service;
import org.example.springboothomework.exceptions.NotFoundException;
import org.example.springboothomework.model.Course;
import org.example.springboothomework.model.Student;
import org.example.springboothomework.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    private final StudentService studentService;
    private final Map<String, Course> courseRepo = new HashMap<>();

    public CourseService(StudentService studentService) {
        this.studentService = studentService;
    }


    public List<Course> getAllCourses() {
        return new ArrayList<>(courseRepo.values());
    }

    public Optional<Course> getCourseById(String id) {
        return Optional.ofNullable(courseRepo.get(id));
    }


    public Course updateCourse(String id, String name, double price, double moneyEarned, Teacher teacher) {
        Course course = courseRepo.get(id);
        if (course == null)
            throw new RuntimeException("Course not found");

        course.setName(name);
        course.setPrice(price);
        course.setMoney_earned(moneyEarned);
        course.setTeacher(teacher);

        return course;
    }


    public boolean deleteCourse(String courseId) {
        return courseRepo.remove(courseId) != null;
    }


    public void assignTeacher(String courseId, Teacher teacher) {
        Course course = courseRepo.get(courseId);
        if (course == null)
            throw new RuntimeException("Course not found");

        course.setTeacher(teacher);
    }

    public void assignStudent(String courseId, Student student){
        Course course =courseRepo.get(courseId);
        if(course == null){
            throw new RuntimeException("Course not found");
        }
        course.setStudent(student);
    }

    public void enrollStudent(String studentId, String courseId) {

        Student student = studentService.getStudentById(studentId)
                .orElseThrow(() -> new NotFoundException("Student is not found"));

        Course course = courseRepo.get(courseId);
        if (course == null) {
            throw new NotFoundException("Course not found with ID: " + courseId);
        }

        if (course.getStudent() == null || !course.getStudent().getStudentId().equals(student.getStudentId())) {
            course.setStudent(student);
            course.setMoney_earned(course.getMoney_earned() + course.getPrice());
        }
    }
}