package org.example.springboothomework;

import org.example.springboothomework.controller.TeacherController;
import org.example.springboothomework.model.Teacher;
import org.example.springboothomework.service.TeacherService;
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
class TeacherControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(teacherController).build();
    }

    @Test
    void shouldCreateTeacher() throws Exception {

        Teacher teacher = new Teacher("Ali", 3000.0);

        when(teacherService.createTeacher(any(Teacher.class))).thenReturn(teacher);

        mockMvc.perform(post("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacher)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnAllTeachers() throws Exception {

        Teacher teacher = new Teacher("Ali", 3000.0);

        when(teacherService.getAllTeachers()).thenReturn(List.of(teacher));

        mockMvc.perform(get("/api/teachers"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnTeacherById() throws Exception {

        Teacher teacher = new Teacher("Ali", 3000.0);

        when(teacherService.getTeacherById("1")).thenReturn(teacher);

        mockMvc.perform(get("/api/teachers/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateTeacher() throws Exception {

        Teacher teacher = new Teacher("Ali", 3000.0);

        when(teacherService.updateTeacher(eq("1"), any(Teacher.class))).thenReturn(teacher);

        mockMvc.perform(put("/api/teachers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacher)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteTeacher() throws Exception {

        doNothing().when(teacherService).deleteTeacher("1");

        mockMvc.perform(delete("/api/teachers/1"))
                .andExpect(status().isNoContent());
    }
}