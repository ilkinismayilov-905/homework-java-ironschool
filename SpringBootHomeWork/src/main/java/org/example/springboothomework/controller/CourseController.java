package org.example.springboothomework.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springboothomework.model.Course;
import org.example.springboothomework.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable String courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable String courseId,
                                               @Valid @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(courseId, course);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{courseId}/teacher")
    public ResponseEntity<String> assignTeacher(@PathVariable String courseId,
                                                @RequestBody String teacher) {
        courseService.assignTeacher(courseId, teacher);
        return ResponseEntity.ok("Teacher assigned successfully");
    }

    @PutMapping("/{courseId}/student")
    public ResponseEntity<String> assignStudent(@PathVariable String courseId,
                                                @RequestBody String student) {
        courseService.assignStudent(courseId, student);
        return ResponseEntity.ok("Student assigned successfully");
    }

    @PostMapping("/{courseId}/enroll/{studentId}")
    public ResponseEntity<String> enrollStudent(@PathVariable String courseId,
                                                @PathVariable String studentId) {
        courseService.enrollStudent(studentId, courseId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Student enrolled successfully");
    }
}