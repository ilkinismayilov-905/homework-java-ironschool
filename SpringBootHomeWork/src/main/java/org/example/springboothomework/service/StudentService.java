package org.example.springboothomework.service;

import org.example.springboothomework.model.Course;
import org.example.springboothomework.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final Map<String, Student> students = new HashMap<>();

    public Student createStudent(String name, String address, String email) {
        Student student = new Student(name, address, email);
        students.put(student.getStudentId(), student);
        return student;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public Optional<Student> getStudentById(String studentId) {
        return Optional.ofNullable(students.get(studentId));
    }

    public void updateStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    public List<Student> getStudentsByCourse(Course course) {
        List<Student> result = new ArrayList<>();
        for (Student s : students.values()) {
            if (s.getCourse() != null && s.getCourse().getCourseId().equals(course.getCourseId())) {
                result.add(s);
            }
        }
        return result;
    }
}
