package ru.hogwarts.school.service;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorie.StudentRepositori;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    StudentRepositori studentRepositorimock = mock(StudentRepositori.class);
    StudentService studentService = new StudentService(studentRepositorimock);

    @Test
    void addStudent() {
        Student student = new Student(1L, "aaa", 1);
        Student stu = studentService.addStudent(student);
        assertEquals(student, stu);
    }
    @Test
    void changeStudent() {
        Student student = new Student(1L, "aaa", 1);
        Mockito.when(studentRepositorimock.save(student)).thenReturn(student);
        studentService.addStudent(student);
        assertEquals(student, studentService.changeStudent(student));
        assertEquals(null, studentService.changeStudent(new Student(44L, "String" , 23)));
    }

    @Test
    void deleteStudent() {
        Student student = new Student(1L, "aaa", 1);
        studentService.addStudent(student);

    }

    @Test
    void allStudentOfAge() {
        Student student = new Student(1L, "aaa", 1);
        List <Student> studentMap = new ArrayList<>();
        assertEquals(studentMap,studentService.allStudentOfAge(33L));
        Mockito.when(studentRepositorimock.findByAge(1L)).thenReturn(studentMap);
        assertEquals(studentMap, studentService.allStudentOfAge(1L));
       studentService.addStudent(student);
        Mockito.when(studentRepositorimock.findByAge(1L)).thenReturn(studentMap);

    }
}