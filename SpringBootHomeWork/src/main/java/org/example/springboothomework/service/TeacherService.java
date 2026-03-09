package org.example.springboothomework.service;

import lombok.RequiredArgsConstructor;
import org.example.springboothomework.exceptions.NotFoundException;
import org.example.springboothomework.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeacherService {

    private final Map<String, Teacher> teachers = new HashMap<>();

    public Teacher createTeacher(Teacher teacher) {
        teachers.put(teacher.getTeacherId(), teacher);
        return teacher;
    }

    public List<Teacher> getAllTeachers() {
        return new ArrayList<>(teachers.values());
    }

    public Teacher getTeacherById(String teacherId) {
        Teacher teacher = teachers.get(teacherId);
        if (teacher == null) {
            throw new NotFoundException("Teacher not found");
        }
        return teacher;
    }

    public Teacher updateTeacher(String teacherId, Teacher updatedTeacher) {

        if (updatedTeacher == null) {
            throw new RuntimeException("Teacher is null");
        }

        if (!teachers.containsKey(teacherId)) {
            throw new NotFoundException("Teacher not found");
        }

        Teacher teacher = getTeacherById(teacherId);

        if (updatedTeacher.getName() != null) {
            teacher.setName(updatedTeacher.getName());
        }

        if (updatedTeacher.getSalary() != null) {
            teacher.setSalary(updatedTeacher.getSalary());
        }

        teachers.put(teacherId, teacher);

        return teacher;
    }

    public void deleteTeacher(String teacherId) {
        if (!teachers.containsKey(teacherId)) {
            throw new NotFoundException("Teacher not found");
        }
        teachers.remove(teacherId);
    }

    public double getTotalSalary() {
        return teachers.values().stream().mapToDouble(Teacher::getSalary).sum();
    }
}
