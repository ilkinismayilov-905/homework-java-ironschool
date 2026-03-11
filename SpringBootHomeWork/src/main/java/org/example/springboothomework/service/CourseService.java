package org.example.springboothomework.service;

import lombok.RequiredArgsConstructor;
import org.example.springboothomework.exceptions.NotFoundException;
import org.example.springboothomework.model.Course;
import org.example.springboothomework.model.Student;
import org.example.springboothomework.model.Teacher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final Map<String, Course> courseRepo = new HashMap<>();

    public CourseService(@Lazy StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    public Course addCourse(Course course) {
        courseRepo.put(course.getCourseId(), course);
        return course;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courseRepo.values());
    }

    public Course getCourseById(String id) {
        Course course = courseRepo.get(id);
        if (course == null) {
            throw new NotFoundException("Course not found by id: " + id);
        }
        return course;
    }


    public Course updateCourse(String id, Course course) {
        if (course == null) {
            throw new RuntimeException("Course is null");
        }

        Course courseModel = getCourseById(id);
        Teacher teacher = teacherService.getTeacherById(courseModel.getTeacherId());
        Student student = studentService.getStudentById(course.getStudentId());

        if (course.getName() != null) courseModel.setName(course.getName());
        if (course.getTeacherId() != null) courseModel.setTeacherId(course.getTeacherId());
        if (course.getStudentId() != null) courseModel.setStudentId(course.getStudentId());
        if (course.getPrice() != null) courseModel.setPrice(course.getPrice());
        if (course.getMoney_earned() != null) courseModel.setMoney_earned(course.getMoney_earned());

        courseRepo.put(id, courseModel);

        return course;
    }


    public void deleteCourse(String courseId) {
        if (!courseRepo.containsKey(courseId)) {
            throw new NotFoundException("Course not found");
        }
        courseRepo.remove(courseId);
    }


    public void assignTeacher(String courseId, String teacher) {
        Course course = courseRepo.get(courseId);
        if (course == null)
            throw new RuntimeException("Course not found");

        course.setTeacherId(teacher);
    }

    public void assignStudent(String courseId, String student) {
        Course course = courseRepo.get(courseId);
        if (course == null) {
            throw new RuntimeException("Course not found");
        }
        course.setStudentId(student);
    }

    public void enrollStudent(String studentId, String courseId) {

        Student student = studentService.getStudentById(studentId);

        Course course = courseRepo.get(courseId);
        if (course == null) {
            throw new NotFoundException("Course not found with ID: " + courseId);
        }

        if (course.getStudentId() == null || !course.getStudentId().equals(student.getStudentId())) {
            course.setStudentId(studentId);
            course.setMoney_earned(course.getMoney_earned() + course.getPrice());
        }
    }

    public double getTotalMoneyEarned() {
        return courseRepo.values()
                .stream()
                .mapToDouble(Course::getMoney_earned)
                .sum();
    }
}