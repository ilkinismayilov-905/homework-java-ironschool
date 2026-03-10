package org.example.springboothomework;

import org.example.springboothomework.controller.StudentController;
import org.example.springboothomework.model.Student;
import org.example.springboothomework.service.StudentService;
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
class StudentControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void shouldReturnAllStudents() throws Exception {
        Student student = new Student("Yusif", "Baku", "yusif@mail.com");
        when(studentService.getAllStudents()).thenReturn(List.of(student));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnStudentById() throws Exception {
        Student student = new Student("Yusif", "Baku", "yusif@mail.com");
        when(studentService.getStudentById("1")).thenReturn(student);

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404WhenStudentNotFound() throws Exception {
        when(studentService.getStudentById("invalid-id")).thenReturn(null);

        mockMvc.perform(get("/students/invalid-id"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateStudent() throws Exception {
        Student student = new Student("Yusif", "Baku", "yusif@mail.com");
        when(studentService.createStudent("Yusif", "Baku", "yusif@mail.com")).thenReturn(student);

        mockMvc.perform(post("/students")
                        .param("name", "Yusif")
                        .param("address", "Baku")
                        .param("email", "yusif@mail.com"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateStudent() throws Exception {
        Student student = new Student("Yusif", "Baku", "yusif@mail.com");
        when(studentService.updateStudent(eq("1"), any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnEmptyListWhenNoStudentsForCourse() throws Exception {
        when(studentService.getStudentsByCourseId("empty-course")).thenReturn(List.of());

        mockMvc.perform(get("/students/course/empty-course"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}