package org.example.springboothomework.service;

import org.example.springboothomework.exceptions.NotFoundException;
import org.example.springboothomework.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class TeacherService {

    private final Map<String, Teacher> teachers = new HashMap<>();

    public Teacher createTeacher(Teacher teacher) {
        teachers.put(teacher.getTeacherId(), teacher);
        return teacher;
    }

    public Collection<Teacher> getAllTeachers() {
        return teachers.values();
    }

    public Teacher getTeacherById(String teacherId) {
        Teacher teacher = teachers.get(teacherId);
        if (teacher == null) {
            throw new NotFoundException("Teacher not found");
        }
        return teacher;
    }

    public Teacher updateTeacher(String teacherId, Teacher updatedTeacher) {
        Teacher teacher = getTeacherById(teacherId);
        teacher.setName(updatedTeacher.getName());
        teacher.setSalary(updatedTeacher.getSalary());
        return teacher;
    }

    public void deleteTeacher(String teacherId) {
        if (!teachers.containsKey(teacherId)) {
            throw new NotFoundException("Teacher not found");
        }
        teachers.remove(teacherId);
    }

    public double getTotalSalary() {
        return teachers.values()
                .stream()
                .mapToDouble(Teacher::getSalary)
                .sum();
    }
}
