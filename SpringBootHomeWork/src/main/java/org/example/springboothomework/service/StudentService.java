package org.example.springboothomework.service;

import lombok.RequiredArgsConstructor;
import org.example.springboothomework.exceptions.NotFoundException;
import org.example.springboothomework.model.Course;
import org.example.springboothomework.model.Student;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Map<String, Student> students = new HashMap<>();
    private final CourseService courseService;

    public StudentService(@Lazy CourseService courseService) {
        this.courseService = courseService;
    }

    public Student createStudent(Student student) {
        students.put(student.getStudentId(), student);
        return student;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public Student getStudentById(String studentId) {
        Student student = students.get(studentId);
        if (student == null) {
            throw new NotFoundException("Student not found");
        }
        return students.get(studentId);
    }

    public Student updateStudent(String id, Student student) {

        if (student == null) {
            throw new RuntimeException("Student is null");
        }

        Student studentModel = getStudentById(id);

        if (student.getName() != null) {
            studentModel.setName(student.getName());
        }

        if (student.getCourseId() != null) {
            studentModel.setCourseId(student.getCourseId());
        }
        if (student.getEmail() != null) {
            studentModel.setEmail(student.getEmail());
        }

        if (student.getAddress() != null) {
            studentModel.setAddress(student.getAddress());
        }

        students.put(id, studentModel);

        return studentModel;
    }

    public List<Student> getStudentsByCourseId(String courseId) {
        courseService.getCourseById(courseId);

        return students.values()
                .stream()
                .filter(s -> s.getCourseId() != null &&
                        s.getCourseId().equals(courseId))
                .collect(Collectors.toList());
    }
}
