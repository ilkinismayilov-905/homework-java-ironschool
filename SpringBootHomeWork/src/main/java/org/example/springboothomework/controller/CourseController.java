package org.example.springboothomework.controller;

import org.example.springboothomework.model.Course;
import org.example.springboothomework.model.Student;
import org.example.springboothomework.model.Teacher;
import org.example.springboothomework.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable String id, @RequestBody Course course) {
        return courseService.updateCourse(
                id,
                course.getName(),
                course.getPrice(),
                course.getMoney_earned(),
                course.getTeacher()
        );
    }

    @DeleteMapping("/{courseId}")
    public String deleteCourse(@PathVariable String courseId) {
        boolean deleted = courseService.deleteCourse(courseId);

        if (deleted) {
            return "Course deleted successfully";
        }

        throw new RuntimeException("Course not found");
    }

    @PutMapping("/{courseId}/teacher")
    public String assignTeacher(@PathVariable String courseId, @RequestBody Teacher teacher) {
        courseService.assignTeacher(courseId, teacher);
        return "Teacher assigned successfully";
    }

    @PutMapping("/{courseId}/student")
    public String assignStudent(@PathVariable String courseId, @RequestBody Student student) {
        courseService.assignStudent(courseId, student);
        return "Student assigned successfully";
    }

    @PostMapping("/{courseId}/enroll/{studentId}")
    public String enrollStudent(@PathVariable String courseId, @PathVariable String studentId) {
        courseService.enrollStudent(studentId, courseId);
        return "Student enrolled successfully";
    }
}