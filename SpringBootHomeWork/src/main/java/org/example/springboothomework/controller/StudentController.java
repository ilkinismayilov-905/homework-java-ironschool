package org.example.springboothomework.controller;

import org.example.springboothomework.model.Student;
import org.example.springboothomework.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String email
    ) {
        return studentService.createStudent(name, address, email);
    }


    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }


    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable String id,
            @RequestBody Student student
    ) {
        return studentService.updateStudent(id, student);
    }


    @GetMapping("/course/{courseId}")
    public List<Student> getStudentsByCourse(
            @PathVariable String courseId
    ) {
        return studentService.getStudentsByCourseId(courseId);
    }
}