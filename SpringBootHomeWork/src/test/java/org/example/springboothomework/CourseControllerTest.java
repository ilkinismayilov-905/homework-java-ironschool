package org.example.springboothomework;

import org.example.springboothomework.controller.CourseController;
import org.example.springboothomework.model.Course;
import org.example.springboothomework.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    void shouldReturnAllCourses() throws Exception {

        Course course = new Course("Java", 500.0);

        when(courseService.getAllCourses()).thenReturn(List.of(course));

        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnCourseById() throws Exception {

        Course course = new Course("Java", 500.0);

        when(courseService.getCourseById("1")).thenReturn(course);

        mockMvc.perform(get("/api/courses/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateCourse() throws Exception {

        Course course = new Course("Java", 500.0);

        when(courseService.updateCourse(eq("1"), any(Course.class))).thenReturn(course);

        mockMvc.perform(put("/api/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteCourse() throws Exception {

        doNothing().when(courseService).deleteCourse("1");

        mockMvc.perform(delete("/api/courses/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldAssignTeacher() throws Exception {

        doNothing().when(courseService).assignTeacher("1", "Ali");

        mockMvc.perform(put("/api/courses/1/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("Ali"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldAssignStudent() throws Exception {

        doNothing().when(courseService).assignStudent("1", "Veli");

        mockMvc.perform(put("/api/courses/1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("Veli"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldEnrollStudent() throws Exception {

        doNothing().when(courseService).enrollStudent("2", "1");

        mockMvc.perform(post("/api/courses/1/enroll/2"))
                .andExpect(status().isCreated());
    }
}